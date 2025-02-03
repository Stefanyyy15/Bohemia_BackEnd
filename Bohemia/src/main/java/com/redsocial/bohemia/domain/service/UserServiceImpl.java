package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.User;
import com.redsocial.bohemia.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Transactional
    @Override
    public Optional<User> updateUser(Long userId, String fullname, String username, String mail, String password, String profilePhoto, String biography) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFullname(fullname);
            user.setUsername(username);
            user.setMail(mail);
            user.setPassword(password);
            user.setProfilePhoto(profilePhoto);
            user.setBiography(biography);
            return Optional.of(userRepository.save(user));
        }
        System.out.println("User with ID " + userId + " not found.");
        return Optional.empty();
    }

    @Override
    public List<User> getUsersFollowing(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return userOpt.get().getFollowing();
        }
        return List.of();
    }

    @Override
    public List<User> getUsersFollowers(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return userOpt.get().getFollowers();
        }
        return List.of();
    }

    @Override
    public Optional<User> searchUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public boolean followUser(Long currentUserId, Long targetUserId) {
        Optional<User> currentUserOpt = userRepository.findById(currentUserId);
        Optional<User> targetUserOpt = userRepository.findById(targetUserId);

        if (currentUserOpt.isPresent() && targetUserOpt.isPresent()) {
            User currentUser = currentUserOpt.get();
            User targetUser = targetUserOpt.get();

            if (!currentUser.getFollowing().contains(targetUser)) {
                currentUser.getFollowing().add(targetUser);
                userRepository.save(currentUser);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean unfollowUser(Long currentUserId, Long targetUserId) {
        Optional<User> currentUserOpt = userRepository.findById(currentUserId);
        Optional<User> targetUserOpt = userRepository.findById(targetUserId);

        if (currentUserOpt.isPresent() && targetUserOpt.isPresent()) {
            User currentUser = currentUserOpt.get();
            User targetUser = targetUserOpt.get();

            if (currentUser.getFollowing().contains(targetUser)) {
                currentUser.getFollowing().remove(targetUser);
                userRepository.save(currentUser);
                return true;
            }
        }
        return false;
    }

    public boolean verifyUser(String name, String password) {
        User verifi = userRepository.findByMail(name).get();

        if (verifi == null) {
            return false;
        }

        if (!verifi.getPassword().equals(password)) {
            return false;
        }

        return true;
    }
    
   
}
