package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.FoodException;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Request.CreateFoodRequest;
import com.springbootproject.ChandoEats.model.Category;
import com.springbootproject.ChandoEats.model.Food;
import com.springbootproject.ChandoEats.model.Restaurant;

import java.util.List;


public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category,
                           Restaurant restaurant) throws FoodException, RestaurantException;

    void deleteFood(Long foodId) throws FoodException;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword) throws  FoodException;

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
