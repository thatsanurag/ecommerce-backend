package com.personal.productservice.mapper;

import com.personal.productservice.dto.CategoryRequestDTO;
import com.personal.productservice.dto.CategoryResponseDTO;
import com.personal.productservice.exception.CategoryNotFoundException;
import com.personal.productservice.models.Category;

public class CategoryMapper {

    public static Category getCategoryFromCategoryRequestDTO(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO == null) {
            throw new CategoryNotFoundException("Category not found!!");
        }
        Category category = new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getDescription());
        return category;
    }
    public static CategoryResponseDTO getCategoryResponseDTOFromCategory(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        if (category != null) {
            categoryResponseDTO.setCategoryId(category.getId());
            categoryResponseDTO.setName(category.getName());
            categoryResponseDTO.setDescription(category.getDescription());
        }
        return categoryResponseDTO;
    }
}
