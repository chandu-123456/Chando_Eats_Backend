package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.OrderException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Response.ApiResponse;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.OrderService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException{
        if(orderId!=null){
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order deleted with ID: "+orderId);
        }
        else{
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(
            @PathVariable("orderId") Long orderId,
            @PathVariable String orderStatus
    ) throws OrderException, UserException {

        Order updatedOrder = orderService.updateOrder(orderId, orderStatus);

        return ResponseEntity.ok(updatedOrder);
    }


}
