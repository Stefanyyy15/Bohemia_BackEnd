
package com.redsocial.bohemia.persistence.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;
    private String message;
    private boolean read = false;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    @Temporal(TemporalType.DATE)
    private Date dateNotification;
    
    @PrePersist
    public void prePersist() {
        this.dateNotification = new Date();
    }

    public Notification() {
    }

    public Notification(String message, User user, Date dateNotification) {
        this.message = message;
        this.user = user;
        this.dateNotification = dateNotification;
    }

    public Long getId_notification() {
        return id_notification;
    }

    public void setId_notification(Long id_notification) {
        this.id_notification = id_notification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Override
    public String toString() {
        return "Notification{" + "id_notification=" + id_notification + ", message=" + message + ", read=" + read + ", user=" + user + ", dateNotification=" + dateNotification + '}';
    }
}
