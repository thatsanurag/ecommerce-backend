package com.personal.productservice.mapper;

import com.personal.productservice.FakeStoreAPI.FakeStoreCartProduct;
import com.personal.productservice.FakeStoreAPI.FakeStoreCartResponse;
import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.dto.CartItemResponseDTO;
import com.personal.productservice.dto.CartResponseDTO;
import com.personal.productservice.dto.ProductResponseDTO;
import com.personal.productservice.exception.CartIsEmptyException;
import com.personal.productservice.models.Cart;
import com.personal.productservice.models.CartItem;
import com.personal.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartMapper {

    private static final String Product_URL = "https://fakestoreapi.com/products/{id}";

    public static Cart getCartFromFakeStoreResponse(FakeStoreCartResponse responseEntity, RestTemplate restTemplate) {
        if(responseEntity == null) {
            throw new CartIsEmptyException("Cart is empty");
        }
        Cart cart = new Cart();
        cart.setId(responseEntity.getId());
        cart.setUserId(responseEntity.getUserId());

        List<CartItem> cartItems = new ArrayList<>();
        for(FakeStoreCartProduct fakeStoreCartProduct : responseEntity.getProducts()) {
            ResponseEntity<FakeStoreProductResponse> productResponse = restTemplate.getForEntity(Product_URL, FakeStoreProductResponse.class,
                    fakeStoreCartProduct.getProductId());

            FakeStoreProductResponse body = productResponse.getBody();

            if(body != null) {
                Product product = ProductMapper.getProductFromFakeStoreResponse(body);
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(fakeStoreCartProduct.getQuantity());
                cartItems.add(cartItem);
            }
        }


        cart.setCartItems(cartItems);
        return cart;
    }

    public static ArrayList<Cart> getCartsFromFakeStoreResponse(ArrayList<FakeStoreCartResponse> responseEntity, RestTemplate restTemplate) {
        ArrayList<Cart> carts = new ArrayList<>();
        for(FakeStoreCartResponse fakeStoreCartResponse:responseEntity) {
            carts.add(getCartFromFakeStoreResponse(fakeStoreCartResponse, restTemplate));
        }
        if(carts.isEmpty()) {
            throw new CartIsEmptyException("Cart is empty");
        }
        return carts;
    }

    public static CartResponseDTO getCartResponseDTOFromCart(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setCartId(cart.getCartId());
        cartResponseDTO.setUserId(cart.getUserId());

        cartResponseDTO.setProducts(getCartItemResponseDTOsFromCart(cart.getCartItems()));
        return cartResponseDTO;
    }

    public static List<CartItemResponseDTO> getCartItemResponseDTOsFromCart(List<CartItem> cartItems) {
        List<CartItemResponseDTO> cartItemResponseDTOs = new ArrayList<>();
        for(CartItem cartItem:cartItems) {
            CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
            cartItemResponseDTO.setProductDetails(ProductMapper.getProductResponseDTOFromProduct(cartItem.getProduct()));
            cartItemResponseDTO.setQuantity(cartItem.getQuantity());
            cartItemResponseDTOs.add(cartItemResponseDTO);
        }
        return cartItemResponseDTOs;
    }
}
