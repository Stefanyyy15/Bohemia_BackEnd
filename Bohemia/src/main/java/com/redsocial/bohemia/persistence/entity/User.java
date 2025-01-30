
package com.redsocial.bohemia.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String fullname;
    @Column(length = 15, unique = true)
    private String username;
    @Column(unique = true)
    private String mail;
    @Column(nullable = false)
    private String password;
    private String profilePhoto;
    @Column(columnDefinition = "TEXT")
    private String biography;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;
     @ManyToMany
    @JoinTable(
        name = "followers", 
        joinColumns = @JoinColumn(name = "id_user"), 
        inverseJoinColumns = @JoinColumn(name = "id_follower")
    )
    @JsonIgnore
    private List<User> following = new ArrayList<>();
    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    private List<User> followers = new ArrayList<>();

    public User() {
    }

    public User(String fullname, String username, String mail, String password, String profilePhoto, String biography, List<Post> posts, List<Comment> comments, List<Notification> notifications) {
        this.fullname = fullname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.biography = biography;
        this.posts = posts;
        this.comments = comments;
        this.notifications = notifications;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", fullname=" + fullname + ", username=" + username + ", mail=" + mail + ", password=" + password + ", profilePhoto=" + profilePhoto + ", biography=" + biography + ", posts=" + posts + ", comments=" + comments + ", notifications=" + notifications + ", following=" + following + ", followers=" + followers + '}';
    }
}
