package com.redsocial.bohemia.web.controller;

import com.redsocial.bohemia.domain.service.NotificationServiceImpl;
import com.redsocial.bohemia.persistence.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/users/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable Long userId) {
        System.out.println("Obteniendo TODAS las notificaciones para el usuario: " + userId);
        return notificationImpl.getNotificationsByUser(userId);
    }

    @GetMapping("/{id}")
    public Optional<Notification> getfindById(@PathVariable("id") Long id) {
        return notificationImpl.findNotification(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationImpl.saveNotification(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationImpl.delNotification(id);
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications() {
        return notificationImpl.listNotification();
    }

    @PutMapping("/read/{id}")
    public String markAsRead(@PathVariable Long id) {
        Optional<Notification> notificationOpt = notificationImpl.findNotification(id);

        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            notificationImpl.saveNotification(notification);
            return "Notificación marcada como leída.";
        }

        return "Error: Notificación no encontrada.";
    }

    @GetMapping("/unread/{userId}")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        System.out.println("Recibiendo solicitud de notificaciones no leídas para el usuario: " + userId);
        List<Notification> notifications = notificationImpl.getUnreadNotifications(userId);
        System.out.println("Notificaciones enviadas: " + notifications);
        return notifications;
    }

}
