package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperAdminController {

    private UserService userService;
    public SuperAdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/api/customers")
    public ResponseEntity<List<User>> getAllCustomers(){
        List<User> users=userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<List<User>> getPenddingRestaurantUser(){
        List<User> users=userService.getPenddingRestaurantOwner();
        return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
    }

}
