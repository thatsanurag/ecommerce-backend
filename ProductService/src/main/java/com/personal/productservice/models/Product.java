package com.personal.productservice.models;

import com.personal.productservice.FakeStoreAPI.Rating;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseModel {
    String title;
    String description;
    String price;
    String image;
    String category;
    Rating rating;
}
