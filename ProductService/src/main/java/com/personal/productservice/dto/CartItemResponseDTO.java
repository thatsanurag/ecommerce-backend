package com.personal.productservice.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {
    private ProductResponseDTO productDetails;
    private int quantity;
}
