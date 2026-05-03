package com.springbootproject.ChandoEats.Request;


import com.springbootproject.ChandoEats.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private Long restaurantId;

    private Address deliveryAddress;


}