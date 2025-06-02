package com.personal.productservice.FakeStoreAPI;

import com.personal.productservice.models.Category;
import lombok.Data;

@Data
public class FakeStoreProductResponse {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
