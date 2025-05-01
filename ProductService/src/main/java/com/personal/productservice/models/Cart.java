package com.personal.productservice.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart extends BaseModel{
    int cartId;
    int userId;
    private List<CartItem> cartItems;
}
