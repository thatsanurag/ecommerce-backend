package com.personal.productservice.mapper;

import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.dto.CategoryResponseDTO;
import com.personal.productservice.dto.ProductRequestDTO;
import com.personal.productservice.dto.ProductResponseDTO;
import com.personal.productservice.exception.CategoryNotFoundException;
import com.personal.productservice.models.Category;
import com.personal.productservice.models.Product;
import com.personal.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static Product getProductFromFakeStoreResponse(FakeStoreProductResponse dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        //Category category = categoryService.findOrCreateByName(dto.getCategory().getName());
        //product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        //product.setRating(dto.getRating());
        return product;
    }

    public static List<Product> getProductsFromFakeStoreResponse(FakeStoreProductResponse[] dtoList) {
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponse dto : dtoList) {
            products.add(getProductFromFakeStoreResponse(dto));
        }
        return products;
    }


    //Create Request DTO

    public Product getProductFromCreateRequestDTO(ProductRequestDTO dto) {
        Product product = new Product();
        //product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        Category category = categoryRepository.findByNameIgnoreCase(dto.getCategoryName())
                .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryName()));
        product.setCategory(category);
        product.setTitle(dto.getTitle());
        product.setImage(dto.getImage());
        product.setRating(dto.getRating());
        return product;
    }

    public static ProductResponseDTO getProductResponseDTOFromProduct(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setProductTitle(product.getTitle());
        productResponseDTO.setProductDescription(product.getDescription());
        //productResponseDTO.setProductCategory(product.getCategory());
        productResponseDTO.setRating(product.getRating());
        productResponseDTO.setImage(product.getImage());
        //Category category = product.getCategory();
        if(product.getCategory() == null) {
            throw new CategoryNotFoundException("Category does not exist!!");
        }
        CategoryResponseDTO catDTO = new CategoryResponseDTO();
        catDTO.setCategoryId(product.getCategory().getId());
        catDTO.setName(product.getCategory().getName());
        catDTO.setDescription(product.getCategory().getDescription());
        productResponseDTO.setProductCategory(catDTO);
        return productResponseDTO;
    }

    public static List<ProductResponseDTO> getProductResponseDTOListFromProduct(List<Product> getAllProducts) {
        List<ProductResponseDTO> products = new ArrayList<>();
        for (Product product : getAllProducts) {
            products.add(getProductResponseDTOFromProduct(product));
        }
        return products;
    }
}
