package com.springbootproject.ChandoEats.repository;

import com.springbootproject.ChandoEats.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
