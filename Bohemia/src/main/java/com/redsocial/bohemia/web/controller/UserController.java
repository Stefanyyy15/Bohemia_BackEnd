package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.UserServiceImpl;
import com.redsocial.bohemia.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userImpl;

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
}