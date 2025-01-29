package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.Comment;
import com.redsocial.bohemia.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
