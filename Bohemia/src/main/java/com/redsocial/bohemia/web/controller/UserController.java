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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional<User> getUserById(@PathVariable Long id) {
        return userImpl.findUser(id);
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
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userImpl.updateUser(
                id,
                request.getFullname(),
                request.getUsername(),
                request.getMail(),
                request.getPassword(),
                request.getProfilePhoto(),
                request.getBiography()
        );
    }

    public static class UserUpdateRequest {

        private String fullname;
        private String username;
        private String mail;
        private String password;
        private String profilePhoto;
        private String biography;

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

        public String getBiography() {
            return biography;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }
    }
    
    @GetMapping("/username/{username}")
    public Optional<User> getfindUserByUsername(@PathVariable String username) {
        return userImpl.findUserByUsername(username);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<User>> searchUsersByUsername(@RequestParam String term) {
        Optional<User> users = userImpl.searchUsersByUsername(term);
        return ResponseEntity.ok(users);
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

}
