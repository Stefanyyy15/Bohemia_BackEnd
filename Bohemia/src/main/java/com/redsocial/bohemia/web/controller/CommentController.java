package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.CommentServiceImpl;
import com.redsocial.bohemia.persistence.entity.Comment;
import com.redsocial.bohemia.persistence.entity.Post;
import com.redsocial.bohemia.persistence.entity.User;
import com.redsocial.bohemia.domain.repository.UserRepository;
import com.redsocial.bohemia.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentServiceImpl commentImpl;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentController(CommentServiceImpl commentImpl, UserRepository userRepository, PostRepository postRepository) {
        this.commentImpl = commentImpl;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Comment> getAllComment() {
        return commentImpl.listComment();
    }

    @GetMapping("/{id}")
    public Optional<Comment> getFindById(@PathVariable Long id) {
        return commentImpl.findComment(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Comment createComment(@RequestBody Comment comment) {
        if (comment.getComment() == null || comment.getComment().trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío");
        }
        Optional<User> userOpt = userRepository.findById(comment.getUser().getId_user());
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        User user = userOpt.get();
        comment.setUser(user);
        Optional<Post> postOpt = postRepository.findById(comment.getPost().getPostId());
        if (!postOpt.isPresent()) {
            throw new IllegalArgumentException("El post no existe");
        }
        Post post = postOpt.get();
        comment.setPost(post);

        return commentImpl.saveComment(comment);
    }
    
    @DeleteMapping("/{id}")
    public void delComment(@PathVariable Long id) {
        commentImpl.delComment(id);
    }
    
    
}
