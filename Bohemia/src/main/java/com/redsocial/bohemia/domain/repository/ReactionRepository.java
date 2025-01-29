package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.Reaction;
import com.redsocial.bohemia.persistence.entity.Post;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Optional<Reaction> findByUserAndPost(User user, Post post);
}
