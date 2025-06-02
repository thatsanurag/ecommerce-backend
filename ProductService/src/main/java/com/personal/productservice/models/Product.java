package com.personal.productservice.models;

import com.personal.productservice.FakeStoreAPI.Rating;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String price;
    private String image;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    @Embedded
    private Rating rating;
}
