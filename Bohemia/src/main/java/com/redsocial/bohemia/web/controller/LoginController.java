package com.redsocial.bohemia.web.controller;


import com.redsocial.bohemia.domain.security.JWTAuthtenticationConfig;
import com.redsocial.bohemia.domain.service.UserServiceImpl;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserServiceImpl imp;

    @PostMapping("/login")
public ResponseEntity<?> login(
        @RequestParam("mail") String mail,
        @RequestParam("password") String password) {

    if (imp.verifyUser(mail, password)) {
        String token = jwtAuthtenticationConfig.getJWTToken(mail);

        // Obtener el usuario desde la base de datos
        Optional<User> userOptional = imp.findUserByMail(mail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Asignar el token al usuario
            user.setToken(token); // Asegúrate de que tu clase User tenga un campo 'token' con un setter

            // Crear la respuesta con el usuario y el token
            Map<String, Object> response = new HashMap<>();
            response.put("user", user); // Devolver el usuario con el token
            response.put("token", token); // Incluir el token en la respuesta

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }
    return ResponseEntity.status(400).body("Credenciales incorrectas");
}


}


