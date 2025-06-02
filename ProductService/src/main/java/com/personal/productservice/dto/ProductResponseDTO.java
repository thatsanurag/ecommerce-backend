package com.personal.productservice.dto;

import com.personal.productservice.FakeStoreAPI.Rating;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;
    private String productTitle;
    private String productDescription;
    private CategoryResponseDTO productCategory;
    private String image;
    private Rating rating;
}
