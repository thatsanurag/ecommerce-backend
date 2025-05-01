package com.personal.productservice.FakeStoreAPI;

import lombok.Data;

@Data
public class FakeStoreProductResponse {
    Long id;
    String title;
    Double price;
    String description;
    String category;
    String image;
    Rating rating;
}
