package com.personal.productservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Category extends BaseModel{

    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
