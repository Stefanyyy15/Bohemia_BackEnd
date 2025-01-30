package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Token;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenService {
    
    Token saveToken(String token, Long userId, LocalDateTime expiresAt);
    Optional<Token> findTokenByValue(String token);
    void revokeToken(String token);
    boolean isTokenExpired(String token);
    void deleteToken(Long tokenId);
}
