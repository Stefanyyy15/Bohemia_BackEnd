package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.Reaction;
import com.redsocial.bohemia.persistence.entity.Post;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Optional<Reaction> findByUserAndPost(User user, Post post);
    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.post.postId = :postId")
    long countByPostId(@Param("postId") Long postId);
    @Query("SELECT r FROM Reaction r WHERE r.user.id = :userId AND r.post.postId = :postId")
    Optional<Reaction> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);
}
