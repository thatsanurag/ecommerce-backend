package com.personal.productservice.dto;

import com.personal.productservice.FakeStoreAPI.Rating;
import com.personal.productservice.models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {
    //private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String image;
    Rating rating;
}
