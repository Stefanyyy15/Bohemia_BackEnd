package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.TokenServiceImpl;
import com.redsocial.bohemia.persistence.entity.Token;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenServiceImpl tokenService;

    public TokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Token createToken(@RequestBody TokenRequest request) {
        return tokenService.saveToken(
                request.getToken(),
                request.getUserId(),
                request.getExpiresAt()
        );
    }

    @GetMapping("/{token}")
    public Optional<Token> getTokenByValue(@PathVariable String token) {
        return tokenService.findTokenByValue(token);
    }

    @PostMapping("/revoke/{token}")
    public void revokeToken(@PathVariable String token) {
        tokenService.revokeToken(token);
    }

    @GetMapping("/is-expired/{token}")
    public boolean isTokenExpired(@PathVariable String token) {
        return tokenService.isTokenExpired(token);
    }

    @DeleteMapping("/{id}")
    public void deleteToken(@PathVariable Long id) {
        tokenService.deleteToken(id);
    }

    public static class TokenRequest {

        private String token;
        private Long userId;
        private LocalDateTime expiresAt;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public LocalDateTime getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
        }
    }

    @PutMapping("/{id}")
    public Token updateToken(
            @PathVariable Long id,
            @RequestBody TokenRequest request) {
        return tokenService.updateToken(
                id,
                request.getToken(),
                request.getUserId(),
                request.getExpiresAt()
        );
    }
}
