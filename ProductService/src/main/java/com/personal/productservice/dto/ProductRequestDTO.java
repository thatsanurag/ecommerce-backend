package com.personal.productservice.dto;

import com.personal.productservice.FakeStoreAPI.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    Rating rating;
}
