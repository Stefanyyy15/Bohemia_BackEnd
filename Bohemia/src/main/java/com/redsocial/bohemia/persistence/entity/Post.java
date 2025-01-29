
package com.redsocial.bohemia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    private String content;
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reaction> reactions;
    
    @PrePersist
    public void prePersist() {
        this.publicationDate = new Date();
    }

    public Post() {
    }

    public Post(Date publicationDate, String content, String image, User user, List<Comment> comments, List<Reaction> reactions) {
        this.publicationDate = publicationDate;
        this.content = content;
        this.image = image;
        this.user = user;
        this.comments = comments;
        this.reactions = reactions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId_post() {
        return id_post;
    }

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", publicationDate=" + publicationDate + ", content=" + content + ", image=" + image + ", user=" + user + ", comments=" + comments + ", reactions=" + reactions + '}';
    }

    
}
