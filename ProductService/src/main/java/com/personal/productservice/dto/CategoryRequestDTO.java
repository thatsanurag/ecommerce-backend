package com.personal.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryRequestDTO {
    //private Long id;
    private String categoryName;
    private String description;
}
