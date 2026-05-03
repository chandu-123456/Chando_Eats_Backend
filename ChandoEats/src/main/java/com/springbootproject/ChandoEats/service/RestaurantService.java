package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Dto.RestaurantDto;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Request.CreateRestaurantRequest;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.model.User;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException;

    public void deleteRestaurant(Long restaurantId) throws RestaurantException;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant>searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws RestaurantException;

    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException;

    public Restaurant updateRestaurantStatus(Long id)throws RestaurantException;
}
