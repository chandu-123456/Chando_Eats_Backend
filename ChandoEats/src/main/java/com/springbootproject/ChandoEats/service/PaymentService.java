package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.PaymentResponse;

public interface PaymentService {

    public PaymentResponse generatePaymentLink(Order order);

}