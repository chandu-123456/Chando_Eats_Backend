package com.springbootproject.ChandoEats.repository;

import com.springbootproject.ChandoEats.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
