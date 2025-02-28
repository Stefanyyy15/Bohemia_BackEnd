package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.CommentRepository;
import com.redsocial.bohemia.persistence.entity.Comment;
import com.redsocial.bohemia.persistence.entity.Post;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Comment saveComment(Comment comment) {
        try {
            Comment savedComment = commentRepository.save(comment);

            Long targetUserId = savedComment.getPost().getUser().getId_user();

            if (!savedComment.getUser().getId_user().equals(targetUserId)) {
                new Thread(() -> {
                    String message = savedComment.getUser().getUsername() + " comentó en tu post: " + savedComment.getComment();
                    notificationService.notifyUser(targetUserId, message);
                }).start();
            }

            return savedComment;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el comentario");
        }
    }

    @Override
    public void delComment(Long id_comment) {
        commentRepository.deleteById(id_comment);
    }

    @Override
    public List<Comment> listComment() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findComment(Long id_comment) {
        return commentRepository.findById(id_comment);
    }

    @Transactional
    @Override
    public Optional<Comment> updateComment(Long id_comment, Date newCommentDate, String newComment) {
        Optional<Comment> commentOpt = commentRepository.findById(id_comment);

        if (commentOpt.isPresent()) {
            Comment com = commentOpt.get();
            com.setComment(newComment);
            com.setCommentDate(newCommentDate);
            commentRepository.save(com); // Guardar cambios
            return Optional.of(com);
        }
        System.out.println("Comment with ID " + id_comment + " not found.");
        return Optional.empty();
    }

    @Override
    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
