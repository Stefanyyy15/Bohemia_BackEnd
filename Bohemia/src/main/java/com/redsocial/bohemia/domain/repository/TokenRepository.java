package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.Token;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUser(User user);
    Optional<Token> findByToken(String token);
    void deleteByUser(User user);
}
