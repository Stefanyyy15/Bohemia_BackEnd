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
            @RequestParam("mail") String mail,
            @RequestParam("password") String password) {

        if (imp.verifyUser(mail, password)) {
            String token = jwtAuthtenticationConfig.getJWTToken(mail);

            UserS user = new UserS(mail, password, token);
            return ResponseEntity.ok(user);
        }
        return null;

    }

}


