package com.personal.productservice.service;

import com.personal.productservice.models.Cart;

import java.util.List;

public interface ICartService {
    Cart getCartById(Long id);
    List<Cart> getAllCart();
    Cart updateCart(Cart cart);
    Cart createCart(Cart cart);
}
