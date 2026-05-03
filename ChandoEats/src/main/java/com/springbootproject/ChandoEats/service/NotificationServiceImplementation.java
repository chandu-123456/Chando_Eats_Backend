package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.model.Notification;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImplementation implements  NotificationService {

    @Autowired
    public NotificationRepository notificationRepository;

    @Override
    public Notification sendOrderStatusNotification(Order order) {
        Notification notification = new Notification();
        notification.setMessage("your order is "+order.getOrderStatus()+ " order id is - "+order.getId());
        notification.setCustomer(order.getCustomer());
        notification.setSentAt(new Date());

        return notificationRepository.save(notification);
    }


    public void sendRestaurantNotification(Restaurant restaurant, String message) {
        // TODO Auto-generated method stub

    }

    public void sendPromotionalNotification(User user, String message) {
        // TODO Auto-generated method stub

    }


    public List<Notification> findUserNotification(Long userId) {
        // TODO Auto-generated method stub
        return notificationRepository.findByCustomerId(userId);
    }


}
