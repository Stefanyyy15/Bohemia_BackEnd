package com.redsocial.bohemia.web.controller;


import com.redsocial.bohemia.domain.security.JWTAuthtenticationConfig;
import com.redsocial.bohemia.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.redsocial.bohemia.persistence.entity.User;
import com.redsocial.bohemia.persistence.entity.UserS;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserServiceImpl imp;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("correo") String correo,
            @RequestParam("contrasena") String contrasena) {

        if (imp.verifyUser(correo, contrasena)) {
            String token = jwtAuthtenticationConfig.getJWTToken(correo);

            UserS user = new UserS(correo, contrasena, token);
            return ResponseEntity.ok(user);
        }
        return null;

    }

}


