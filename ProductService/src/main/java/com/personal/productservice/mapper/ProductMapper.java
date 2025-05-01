package com.personal.productservice.mapper;

import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.dto.ProductRequestDTO;
import com.personal.productservice.dto.ProductResponseDTO;
import com.personal.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static Product getProductFromFakeStoreResponse(FakeStoreProductResponse dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setRating(dto.getRating());
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

    public static Product getProductFromCreateRequestDTO(ProductRequestDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
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
        productResponseDTO.setProductCategory(product.getCategory());
        productResponseDTO.setRating(product.getRating());
        productResponseDTO.setImage(product.getImage());
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
