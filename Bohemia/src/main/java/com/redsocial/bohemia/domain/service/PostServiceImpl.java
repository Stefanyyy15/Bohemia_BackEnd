package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.PostRepository;
import com.redsocial.bohemia.persistence.entity.Post;
import com.redsocial.bohemia.persistence.entity.User;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delPost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> listPost() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findPost(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> getPostsByUser(Long id_user) {
        // Primero necesitamos obtener el User
        User user = new User();
        user.setId_user(id_user);
        return postRepository.findByUser(user);
    }

    @Transactional
    @Override
    public Post updatePost(Long postId, Date newPublicationDate, String newContent, String newImage) {
        Optional<Post> postOpt = postRepository.findById(postId);

        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            post.setPublicationDate(newPublicationDate);
            post.setContent(newContent);
            post.setImage(newImage);
            return postRepository.save(post);
        }
        System.out.println("Post with ID " + postId + " not found.");
        return null;
    }

}
