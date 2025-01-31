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
import org.springframework.web.bind.annotation.PutMapping;
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
}