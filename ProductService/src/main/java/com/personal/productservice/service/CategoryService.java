package com.personal.productservice.service;

import com.personal.productservice.exception.CategoryNotFoundException;
import com.personal.productservice.models.Category;
import com.personal.productservice.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Optional<Category> findOrCreateByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()->
                new CategoryNotFoundException("Category not found"));
        if(category.getName() != null) {
            existingCategory.setName(category.getName());
        }
        if(category.getDescription() != null) {
            existingCategory.setDescription(category.getDescription());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    @Override
    public Category createCategory(Category category) {
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->
                new CategoryNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
