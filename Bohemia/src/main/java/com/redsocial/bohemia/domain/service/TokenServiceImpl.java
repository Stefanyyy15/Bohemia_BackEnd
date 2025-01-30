package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Token;
import com.redsocial.bohemia.persistence.entity.User;
import com.redsocial.bohemia.domain.repository.TokenRepository;
import com.redsocial.bohemia.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Token saveToken(String token, Long userId, LocalDateTime expiresAt) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Token newToken = new Token(token, expiresAt, user);
            return tokenRepository.save(newToken);
        }
        System.out.println("User with ID " + userId + " not found.");
        return null; // Puedes lanzar una excepción si prefieres
    }

    @Override
    public Optional<Token> findTokenByValue(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    @Override
    public void revokeToken(String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token existingToken = tokenOpt.get();
            existingToken.setRevoked(true);
            tokenRepository.save(existingToken);
        } else {
            System.out.println("Token not found: " + token);
        }
    }

    @Override
    public boolean isTokenExpired(String token) {
        Optional<Token> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isPresent()) {
            Token existingToken = tokenOpt.get();
            return existingToken.getExpiresAt().isBefore(LocalDateTime.now());
        }
        return false; 
    }

    @Override
    public void deleteToken(Long tokenId) {
        tokenRepository.deleteById(tokenId);
    }
}

//// Crear un nuevo token
//Token token = new Token();
//token.setToken("abc123token");
//token.setExpiresAt(LocalDateTime.now().plusHours(1)); // Token que expira en 1 hora
//token.setUser(userService.findUser(1L).get()); // Asignar un usuario con id 1
//tokenService.createToken(token.getToken(), token.getUser().getId_user(), token.getExpiresAt());
