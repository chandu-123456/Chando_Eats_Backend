package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.model.Notification;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.model.User;

import java.util.List;

public interface NotificationService {
    public Notification sendOrderStatusNotification(Order order);
    public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(User user, String message);
    public List<Notification> findUserNotification(Long userId);
}
