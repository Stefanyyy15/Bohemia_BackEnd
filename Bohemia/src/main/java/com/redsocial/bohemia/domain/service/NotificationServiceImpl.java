package com.redsocial.bohemia.domain.service;

import com.redsocial.bohemia.domain.repository.NotificationRepository;
import com.redsocial.bohemia.persistence.entity.Notification;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delNotification (Long id_notification) {
        notificationRepository.deleteById(id_notification);
    }

    @Override
    public List<Notification> listNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findNotification (Long id_notification) {
        return notificationRepository.findById(id_notification);
    
    }

}