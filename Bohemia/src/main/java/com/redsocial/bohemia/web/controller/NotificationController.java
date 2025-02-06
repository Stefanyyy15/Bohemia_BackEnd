package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.NotificationServiceImpl;
import com.redsocial.bohemia.persistence.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationServiceImpl notificationImpl;

    @Autowired
    public NotificationController(NotificationServiceImpl notificationImpl) {
        this.notificationImpl = notificationImpl;
    }

    @GetMapping
    public List<Notification> getAllNotification() {
        return notificationImpl.listNotification();
    }

    @GetMapping("/{id}")
    public Optional<Notification> getfindById(@PathVariable Long id_notification) {
        return notificationImpl.findNotification(id_notification);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationImpl.saveNotification(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationImpl.delNotification(id);
    }

    @PutMapping("/read/{id}")
    public void markAsRead(@PathVariable Long id) {
        Optional<Notification> notification = notificationImpl.findNotification(id);
        notification.ifPresent(n -> {
            n.setRead(true);
            notificationImpl.saveNotification(n);
        });
    }

    @GetMapping("/unread/{userId}")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        return notificationImpl.getUnreadNotifications(userId);
    }

}
