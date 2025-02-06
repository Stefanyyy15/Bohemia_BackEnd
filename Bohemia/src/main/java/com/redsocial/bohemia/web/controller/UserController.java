package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.security.JWTAuthtenticationConfig;
import com.redsocial.bohemia.domain.service.UserServiceImpl;
import com.redsocial.bohemia.persistence.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userImpl;

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    public UserController(UserServiceImpl userImpl) {
        this.userImpl = userImpl;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userImpl.listUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userImpl.findUser(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User user = userOptional.get();
        List<User> followers = userImpl.getUsersFollowers(id);
        List<User> following = userImpl.getUsersFollowing(id); // Agrega este método en el servicio si no existe

        Map<String, Object> response = new HashMap<>();
        response.put("id_user", user.getId_user());
        response.put("fullname", user.getFullname());
        response.put("username", user.getUsername());
        response.put("mail", user.getMail());
        response.put("profilePhoto", user.getProfilePhoto());
        response.put("biography", user.getBiography());
        response.put("followers", followers);
        response.put("following", following);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mail/{mail}")
    public Optional<User> getfindUserByMail(@PathVariable String mail) {
        return userImpl.findUserByMail(mail);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        return userImpl.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        userImpl.deleteUser(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User request) {
        Optional<User> updatedUser = userImpl.updateUser(
                id,
                request.getFullname(),
                request.getUsername(),
                request.getMail(),
                request.getPassword(),
                request.getProfilePhoto(),
                request.getBiography()
        );

        if (!updatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
        }

        return ResponseEntity.ok(updatedUser.get()); // Devuelve el usuario actualizado
    }

    @GetMapping("/username/{username}")
    public Optional<User> getfindUserByUsername(@PathVariable String username) {
        return userImpl.findUserByUsername(username);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String term) {
        List<User> users = userImpl.searchUsers(term);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long userId) {
        try {
            List<User> followers = userImpl.getUsersFollowers(userId);
            if (followers != null && !followers.isEmpty()) {
                return ResponseEntity.ok(followers);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener seguidores: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long userId) {
        try {
            List<User> following = userImpl.getUsersFollowing(userId);
            if (following != null && !following.isEmpty()) {
                return ResponseEntity.ok(following);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener seguidos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{currentUserId}/follow/{targetUserId}")
    public ResponseEntity<String> followUser(@PathVariable Long currentUserId, @PathVariable Long targetUserId) {
        boolean success = userImpl.followUser(currentUserId, targetUserId);
        return success ? ResponseEntity.ok("Seguido correctamente") : ResponseEntity.badRequest().body("No se pudo seguir");
    }

    @PostMapping("/{currentUserId}/unfollow/{targetUserId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long currentUserId, @PathVariable Long targetUserId) {
        boolean success = userImpl.unfollowUser(currentUserId, targetUserId);
        return success ? ResponseEntity.ok("Dejado de seguir correctamente") : ResponseEntity.badRequest().body("No se pudo dejar de seguir");
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, Object> response = new HashMap<>();

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                // Extraer el email del token
                String email = jwtAuthtenticationConfig.extractUsername(token);
                System.out.println("Email extraído del token: " + email); // Para debug

                // Buscar el usuario específico por el email del token
                Optional<User> user = userImpl.findUserByMail(email);

                if (user.isPresent()) {
                    // Verificar que el token del usuario coincida
                    response.put("user", user.get());
                    return ResponseEntity.ok(response);
                } else {
                    response.put("error", "Usuario no encontrado para el token proporcionado");
                    return ResponseEntity.status(404).body(response);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar el token: " + e.getMessage()); // Para debug
                response.put("error", "Token inválido o expirado");
                return ResponseEntity.status(400).body(response);
            }
        }

        response.put("error", "Token no proporcionado");
        return ResponseEntity.status(401).body(response);
    }
    
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkIfUsernameExists(@PathVariable String username) {
        Optional<User> user = userImpl.findUserByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", user.isPresent());
        return ResponseEntity.ok(response);
    }

    // Verificar si el correo ya está registrado
    @GetMapping("/exists/mail/{mail}")
    public ResponseEntity<Map<String, Boolean>> checkIfEmailExists(@PathVariable String mail) {
        Optional<User> user = userImpl.findUserByMail(mail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", user.isPresent()); 
        return ResponseEntity.ok(response);
    }

}
