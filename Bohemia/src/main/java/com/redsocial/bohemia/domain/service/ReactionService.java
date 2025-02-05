package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Reaction;
import java.util.List;
import java.util.Optional;


public interface ReactionService {
    Reaction saveReaction (Reaction reaction);
    void delReaction (Long id_reaction);
    List<Reaction> listReaction();
    Optional<Reaction> findReaction(Long id_reaction);
    Optional<Reaction> findUserReaction(Long userId, Long postId);
    long countLikesByPost(Long postId);
}

