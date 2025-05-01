package com.personal.productservice.dto;

import com.personal.productservice.FakeStoreAPI.Rating;
import lombok.Data;

@Data
public class ProductResponseDTO {
    Long productId;
    String productTitle;
    String productDescription;
    String productCategory;
    String image;
    Rating rating;
}
