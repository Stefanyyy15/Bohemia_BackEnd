package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.Post;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    List<Post> findAllByOrderByPublicationDateDesc();
}
