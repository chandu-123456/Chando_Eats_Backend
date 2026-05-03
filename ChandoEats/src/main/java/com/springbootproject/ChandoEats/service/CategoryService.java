package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory (String name, Long userId) throws RestaurantException;
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public Category findCategoryById(Long id) throws RestaurantException;

}
