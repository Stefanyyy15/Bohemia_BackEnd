package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.persistence.entity.Notification;
import java.util.List;
import java.util.Optional;


public interface NotificationService {
    Notification saveNotification (Notification notification);
    void delNotification(Long id_notification);
    List<Notification> listNotification();
    Optional<Notification> findNotification(Long id_comment);
    void notifyUser(Long userId, String message);
    List<Notification> getUnreadNotifications(Long userId);
    List<Notification> getNotificationsByUser(Long userId);

    
}
