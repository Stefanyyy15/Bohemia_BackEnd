package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.ReactionServiceImpl;
import com.redsocial.bohemia.persistence.entity.Reaction;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reaction")
public class ReactionController {
    
    private final ReactionServiceImpl reactionImpl;
    
    public ReactionController(ReactionServiceImpl reactionImpl) {
        this.reactionImpl = reactionImpl;
    }
    
    @GetMapping
    public List<Reaction> getAllReaction() {
        return reactionImpl.listReaction();
    }
    
    @GetMapping("/{id}")
    public Optional<Reaction> getReactionById (@PathVariable Long id) {
        return reactionImpl.findReaction(id);
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Reaction createReaction(@RequestBody Reaction reaction) {
        return reactionImpl.saveReaction(reaction);
    }
    
    @DeleteMapping("/{id}")
    public void deleteReaction (@PathVariable Long id) {
        reactionImpl.delReaction(id);
    }
}
