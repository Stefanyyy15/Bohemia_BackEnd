package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.ReactionRepository;
import com.redsocial.bohemia.persistence.entity.Reaction;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private NotificationService notificationService;
    
    @Override
    public Reaction saveReaction(Reaction reaction) {
        Optional<Reaction> existingReaction = reactionRepository.findByUserAndPost(reaction.getUser(), reaction.getPost());

        if (existingReaction.isPresent()) {
            reactionRepository.delete(existingReaction.get());
            return null;
        } else {
            Reaction savedReaction = reactionRepository.save(reaction);

            // 🔔 Notificar al autor del post
            User postAuthor = savedReaction.getPost().getUser();
            if (!postAuthor.getId_user().equals(savedReaction.getUser().getId_user())) {
                notificationService.notifyUser(postAuthor.getId_user(), "A alguien le gustó tu publicación.");
            }

            return savedReaction;
        }
    }
    @Override
    public void delReaction(Long id_reaction) {
        reactionRepository.deleteById(id_reaction);
    }

    @Override
    public List<Reaction> listReaction() {
        return reactionRepository.findAll();
    }

    @Override
    public Optional<Reaction> findReaction(Long id_reaction) {
        return reactionRepository.findById(id_reaction);
    }

    @Override
    public long countLikesByPost(Long postId) {
        return reactionRepository.countByPostId(postId);
    }

    @Override
    public Optional<Reaction> findUserReaction(Long userId, Long postId) {
        return reactionRepository.findByUserIdAndPostId(userId, postId);
    }
}
