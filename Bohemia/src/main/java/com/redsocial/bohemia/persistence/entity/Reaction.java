
package com.redsocial.bohemia.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reaction")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reaction;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;

    public Reaction() {
    }

    public Reaction(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Long getId_reaction() {
        return id_reaction;
    }

    public void setId_reaction(Long id_reaction) {
        this.id_reaction = id_reaction;
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    @Override
    public String toString() {
        return "Reaction{" + "id_reaction=" + id_reaction + ", user=" + user + ", post=" + post + '}';
    }
}
