package com.personal.productservice.service;

import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);

    List<Product> getAllProducts();

    //Product createProduct(Product product);
    //Product updateProduct(Long productId, Product product);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);
}
