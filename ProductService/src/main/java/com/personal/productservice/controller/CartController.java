package com.personal.productservice.controller;

import com.personal.productservice.FakeStoreAPI.FakeStoreCartResponse;
import com.personal.productservice.dto.CartResponseDTO;
import com.personal.productservice.mapper.CartMapper;
import com.personal.productservice.models.Cart;
import com.personal.productservice.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public HttpEntity<CartResponseDTO> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        CartResponseDTO cartResponseDTO = CartMapper.getCartResponseDTOFromCart(cart);
        return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
    }

}
