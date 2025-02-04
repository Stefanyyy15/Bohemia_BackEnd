package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.CommentServiceImpl;
import com.redsocial.bohemia.persistence.entity.Comment;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentServiceImpl commentImpl;

    @Autowired
    public CommentController(CommentServiceImpl commentImpl) {
        this.commentImpl = commentImpl;
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
    public Comment createComment(@RequestBody Comment com) {
        if (com.getComment() == null || com.getComment().trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío");
        }
        if (com.getUser() == null || com.getPost() == null) {
            throw new IllegalArgumentException("El comentario debe estar asociado a un usuario y un post");
        }
        return commentImpl.saveComment(com);
    }

    @DeleteMapping("/{id}")
    public void delComment(@PathVariable Long id) {
        commentImpl.delComment(id);
    }

    public static class CommentUpdateRequest {

        private String comment;
        private Date commentDate;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Date getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(Date commentDate) {
            this.commentDate = commentDate;
        }
    }
}
