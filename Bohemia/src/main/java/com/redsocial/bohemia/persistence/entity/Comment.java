
package com.redsocial.bohemia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;
    @Temporal(TemporalType.DATE)
    private Date commentDate;
    @Column(columnDefinition = "TEXT")
    private String comment;
    @Column(nullable = true)
    private int likes;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @PrePersist
    public void prePersist() {
        this.commentDate = new Date();
    }

    public Comment() {
    }

    public Comment(User user, Date commentDate, String comment, int likes, Post post) {
        this.user = user;
        this.commentDate = commentDate;
        this.comment = comment;
        this.likes = likes;
        this.post = post;
    }

    public Long getId_comment() {
        return id_comment;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" + "id_comment=" + id_comment + ", user=" + user + ", commentDate=" + commentDate + ", comment=" + comment + ", likes=" + likes + ", post=" + post + '}';
    }
}
    