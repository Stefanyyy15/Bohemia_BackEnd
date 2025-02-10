package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.ReactionServiceImpl;
import com.redsocial.bohemia.persistence.entity.Reaction;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reaction")
public class ReactionController {

    private final ReactionServiceImpl reactionService;

    public ReactionController(ReactionServiceImpl reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping
    public ResponseEntity<List<Reaction>> getAllReactions() {
        return ResponseEntity.ok(reactionService.listReaction());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reaction> getReactionById(@PathVariable Long id) {
        return reactionService.findReaction(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Reaction> createReaction(@RequestBody Reaction reaction) {
        Reaction savedReaction = reactionService.saveReaction(reaction);
        return savedReaction != null ? ResponseEntity.ok(savedReaction) : ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{postId}/likes")
    public ResponseEntity<Long> getLikesByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(reactionService.countLikesByPost(postId));
    }

    @PostMapping("/like")
    public ResponseEntity<Reaction> addLike(@RequestBody Reaction reaction) {
        Reaction savedReaction = reactionService.saveReaction(reaction);
        return savedReaction != null ? ResponseEntity.ok(savedReaction) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable Long id) {
        reactionService.delReaction(id);
        return ResponseEntity.noContent().build();
    }
}
