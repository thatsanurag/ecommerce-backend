package com.personal.productservice.dto;

import com.personal.productservice.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class CartRequestDTO {
    int cartId;
    int userId;
    List<Product> products;
}
