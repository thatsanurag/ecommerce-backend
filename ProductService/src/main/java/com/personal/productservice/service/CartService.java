package com.personal.productservice.service;

import com.personal.productservice.FakeStoreAPI.FakeStoreCartResponse;
import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.exception.CartIsEmptyException;
import com.personal.productservice.mapper.CartMapper;
import com.personal.productservice.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService implements ICartService {

    private final RestTemplateBuilder builder;

    @Autowired
    public CartService(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Cart getCartById(Long id) {
        RestTemplate restTemplate = builder.build();
        ResponseEntity<FakeStoreCartResponse> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/carts/{id}",
                FakeStoreCartResponse.class, id);
        return CartMapper.getCartFromFakeStoreResponse(responseEntity.getBody(), restTemplate);
    }

    @Override
    public List<Cart> getAllCart() {
        RestTemplate restTemplate = builder.build();
        ArrayList<FakeStoreCartResponse> fakeStoreCartResponses = new ArrayList<>();
        return CartMapper.getCartsFromFakeStoreResponse(fakeStoreCartResponses, restTemplate);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return null;
    }

    @Override
    public Cart createCart(Cart cart) {
        return null;
    }
}
