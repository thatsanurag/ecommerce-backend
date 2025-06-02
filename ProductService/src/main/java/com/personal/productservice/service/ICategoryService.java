package com.personal.productservice.service;

import com.personal.productservice.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Optional<Category> findOrCreateByName(String name);
    Category updateCategory(Long categoryId, Category category);
    void deleteCategory(Long categoryId);
    Category createCategory(Category category);

    Category getCategoryById(Long categoryId);
    List<Category> getAllCategories();
}
