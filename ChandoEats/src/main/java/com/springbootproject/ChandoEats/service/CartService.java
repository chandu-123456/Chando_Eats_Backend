package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.CartException;
import com.springbootproject.ChandoEats.Exception.CartItemException;
import com.springbootproject.ChandoEats.Exception.FoodException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Request.AddCartItemRequest;
import com.springbootproject.ChandoEats.model.Cart;
import com.springbootproject.ChandoEats.model.CartItem;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException,CartItemException;
    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;

}
