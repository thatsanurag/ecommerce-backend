package com.personal.productservice.controller;


import com.personal.productservice.dto.CategoryRequestDTO;
import com.personal.productservice.dto.CategoryResponseDTO;
import com.personal.productservice.mapper.CategoryMapper;
import com.personal.productservice.models.Category;
import com.personal.productservice.repository.CategoryRepository;
import com.personal.productservice.service.CategoryService;
import com.personal.productservice.service.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        CategoryResponseDTO categoryResponseDTO = CategoryMapper.getCategoryResponseDTOFromCategory(category);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories.stream().map(CategoryMapper::getCategoryResponseDTOFromCategory).collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody  CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryService.createCategory(CategoryMapper.getCategoryFromCategoryRequestDTO(categoryRequestDTO));
        CategoryResponseDTO categoryResponseDTO = CategoryMapper.getCategoryResponseDTOFromCategory(category);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                              @RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryService.updateCategory(categoryId, CategoryMapper.getCategoryFromCategoryRequestDTO(categoryRequestDTO));
        CategoryResponseDTO categoryResponseDTO = CategoryMapper.getCategoryResponseDTOFromCategory(category);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
