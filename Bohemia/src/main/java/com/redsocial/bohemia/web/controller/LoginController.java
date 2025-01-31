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
            
            Optional<User> user = imp.findUserByMail(mail);
            
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }
        return null;

    }

}


