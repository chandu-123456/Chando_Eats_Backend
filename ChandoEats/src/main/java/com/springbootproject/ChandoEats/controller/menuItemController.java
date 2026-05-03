package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.FoodException;
import com.springbootproject.ChandoEats.model.Food;
import com.springbootproject.ChandoEats.service.FoodService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class menuItemController {
    @Autowired
    private FoodService menuItemService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name)  throws FoodException{
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getMenuItemByRestaurantId(
            @PathVariable Long restaurantId,
            @RequestParam(required = false)  boolean vegetarian,
            @RequestParam(required = false)  boolean seasonal,
            @RequestParam(required = false)  boolean nonveg,
            @RequestParam(required = false) String food_category) throws FoodException {
        List<Food> menuItems= menuItemService.getRestaurantsFood(
                restaurantId,vegetarian,nonveg,seasonal,food_category);
        return ResponseEntity.ok(menuItems);
    }


}
