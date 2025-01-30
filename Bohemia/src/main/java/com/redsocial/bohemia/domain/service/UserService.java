package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    void deleteUser(Long userId);
    List<User> listUsers();
    Optional<User> findUser(Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByMail(String mail);
    Optional<User> updateUser(Long userId, String fullname, String username, String mail, String password, String profilePhoto, String biography);
    List<User> getUsersFollowing(Long userId);
    List<User> getUsersFollowers(Long userId);
    void followUser(Long userId, Long followUserId);
    void unfollowUser(Long userId, Long followUserId);
}
