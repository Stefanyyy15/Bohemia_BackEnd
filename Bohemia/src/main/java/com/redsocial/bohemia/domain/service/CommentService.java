package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Comment;
import com.redsocial.bohemia.persistence.entity.Post;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface CommentService {
    Comment saveComment(Comment comment);
    void delComment(Long id_comment);
    List<Comment> listComment();
    Optional<Comment> findComment(Long id_comment);
    Optional<Comment> updateComment(Long id_comment, Date newCommentDate, String newComment);
    List<Comment> findCommentsByPost(Post post);
}
