package com.personal.productservice.dto;

import com.personal.productservice.models.Product;
import lombok.Data;
import java.util.List;

@Data
public class CartResponseDTO {
    private int cartId;
    private int userId;
    private List<CartItemResponseDTO> products;
}
