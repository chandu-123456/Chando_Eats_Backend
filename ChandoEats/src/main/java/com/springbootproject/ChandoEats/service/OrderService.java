package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.CartException;
import com.springbootproject.ChandoEats.Exception.OrderException;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Request.CreateOrderRequest;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.PaymentResponse;
import com.springbootproject.ChandoEats.model.User;

import java.util.List;

public interface OrderService {

    public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;


}