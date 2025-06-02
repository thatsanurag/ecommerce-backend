package com.personal.productservice.service;

import com.personal.productservice.exception.ProductNotFoundException;
import com.personal.productservice.models.Category;
import com.personal.productservice.models.Product;
import com.personal.productservice.repository.CategoryRepository;
import com.personal.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "inStoreProductService")
public class InStoreProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InStoreProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product createProduct(Product product) {
        product.setId(product.getId());
        product.setTitle(product.getTitle());
        product.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        Category category = categoryRepository.findByNameIgnoreCase(product.getCategory().getName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        product.setCategory(category);
        product.setImage(product.getImage());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
        if(product.getTitle() != null) {
            existingProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if(product.getImage() != null) {
            existingProduct.setImage(product.getImage());
        }
        if(product.getRating()!= null) {
            existingProduct.setRating(product.getRating());
        }
        return productRepository.save(existingProduct);
    }
}
