package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.NotificationRepository;
import com.redsocial.bohemia.domain.repository.UserRepository;
import com.redsocial.bohemia.persistence.entity.Notification;
import com.redsocial.bohemia.persistence.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        notificationRepository.deleteById(id_notification);
    }

    @Override
    public List<Notification> listNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findNotification(Long id_notification) {
        return notificationRepository.findById(id_notification);

    }

    public void notifyUser(Long userId, String message) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Notification notification = new Notification(message, user, new Date());
        notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return notificationRepository.findByUserAndReadFalse(user);
    }

}
