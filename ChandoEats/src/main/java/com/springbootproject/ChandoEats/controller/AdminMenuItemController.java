package com.springbootproject.ChandoEats.controller;


import com.springbootproject.ChandoEats.Exception.FoodException;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Request.CreateFoodRequest;
import com.springbootproject.ChandoEats.model.Food;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.service.CategoryService;
import com.springbootproject.ChandoEats.service.FoodService;
import com.springbootproject.ChandoEats.service.RestaurantService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/food")
public class AdminMenuItemController {

    @Autowired
    private FoodService menuItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService catgoryService;


@PostMapping()
    public ResponseEntity<Food> createItem(@RequestBody CreateFoodRequest item,@RequestHeader("Authorization") String jwt) throws FoodException, UserException, RestaurantException {
        Restaurant restaurant = restaurantService.findRestaurantById(item.getRestaurantId());
        Food menuItem= menuItemService.createFood(item,item.getCategory(),restaurant);
        return ResponseEntity.ok(menuItem);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) throws FoodException{
      menuItemService.deleteFood(id);
      return ResponseEntity.ok("Food Item is deleted ");
    }



    @GetMapping("/search")
    public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name) throws FoodException{
         List<Food> menuItem=menuItemService.searchFood(name);
         return  ResponseEntity.ok(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvilibilityStatus(@PathVariable Long id) throws FoodException {
      Food menuItems=menuItemService.updateAvailibilityStatus(id);
      return ResponseEntity.ok(menuItems);
    }




}
