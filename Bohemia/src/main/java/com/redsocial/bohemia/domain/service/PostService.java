package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Post;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface PostService {
    Post savePost (Post post);
    void delPost (Long postId);
    List<Post> listPost();
    Post updatePost(Long postId, Date newPublicationDate, String newContent, String newImage);
    Optional<Post> findPost(Long postId);
}
