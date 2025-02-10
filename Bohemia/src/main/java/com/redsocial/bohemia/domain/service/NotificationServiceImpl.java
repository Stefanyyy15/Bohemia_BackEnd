package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.NotificationRepository;
import com.redsocial.bohemia.domain.repository.UserRepository;
import com.redsocial.bohemia.persistence.entity.Notification;
import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delNotification(Long id_notification) {
        if (notificationRepository.existsById(id_notification)) {
            notificationRepository.deleteById(id_notification);
        } else {
            throw new RuntimeException("Notificación no encontrada");
        }
    }

    @Override
    public List<Notification> listNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findNotification(Long id_notification) {
        return notificationRepository.findById(id_notification);
    }

    @Override
    public void notifyUser(Long userId, String message) {
        System.out.println("Intentando notificar al usuario con ID: " + userId + " con mensaje: " + message);
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            Notification notification = new Notification();
            notification.setUser(userOpt.get());
            notification.setMessage(message);
            notification.setRead(false);
            notification.setDateNotification(new Date());

            // Intentamos guardar la notificación
            try {
                Notification savedNotification = saveNotification(notification);
                System.out.println("Notificación guardada: " + savedNotification);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al guardar la notificación.");
            }
        } else {
            System.out.println("Usuario no encontrado para notificación.");
        }
    }

    @Override
    public List<Notification> getNotificationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return notificationRepository.findByUser(user);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return notificationRepository.findByUserAndReadFalse(user);
    }
}
