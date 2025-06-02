package com.personal.productservice.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long categoryId;
    private String name;
    private String description;
}
