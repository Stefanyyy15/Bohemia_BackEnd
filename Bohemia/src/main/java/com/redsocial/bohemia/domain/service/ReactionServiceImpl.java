package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.ReactionRepository;
import com.redsocial.bohemia.persistence.entity.Reaction;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public Reaction saveReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
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
}
