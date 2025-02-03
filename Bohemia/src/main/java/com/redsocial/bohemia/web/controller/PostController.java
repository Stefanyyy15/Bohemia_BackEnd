package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.repository.PostRepository;
import com.redsocial.bohemia.domain.service.PostServiceImpl;
import com.redsocial.bohemia.persistence.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImpl postImpl;

    @Autowired
    public PostController(PostServiceImpl postImpl) {
        this.postImpl = postImpl;
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postImpl.listPost();
    }

    @GetMapping("/user/{id_user}")
public List<Post> getPostsByUser(@PathVariable Long id_user) {
    return postImpl.getPostsByUser(id_user);
}

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postImpl.findPost(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Post createPost(@RequestBody Post post) {
        return postImpl.savePost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postImpl.delPost(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody Post post) {
        return postImpl.updatePost(
                id,
                post.getPublicationDate(),
                post.getContent(),
                post.getImage()
        );
    }
}
