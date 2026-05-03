package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.CartException;
import com.springbootproject.ChandoEats.Exception.OrderException;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Request.CreateOrderRequest;
import com.springbootproject.ChandoEats.Response.ApiResponse;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.PaymentResponse;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.OrderService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse>  createOrder(@RequestBody CreateOrderRequest order,
                                                        @RequestHeader("Authorization") String jwt)
            throws UserException, RestaurantException,
            CartException,
            OrderException{
        User user=userService.findUserProfileByJwt(jwt);
        System.out.println("req user "+user.getEmail());
        if(order!=null) {
            PaymentResponse res = orderService.createOrder(order,user);
            return ResponseEntity.ok(res);
        }else throw new OrderException("Please provide valid request body");
    }



    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt) throws OrderException, UserException{

        User user=userService.findUserProfileByJwt(jwt);

        if(user.getId()!=null) {
            List<Order> userOrders = orderService.getUserOrders(user.getId());
            return ResponseEntity.ok(userOrders);
        }else {
            return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
        }
    }

}
