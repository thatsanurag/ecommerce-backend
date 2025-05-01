package com.personal.productservice.service;

import com.personal.productservice.FakeStoreAPI.FakeStoreProductResponse;
import com.personal.productservice.exception.ProductNotFoundException;
import com.personal.productservice.mapper.ProductMapper;
import com.personal.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static com.personal.productservice.utility.HttpUtil.putForEntity;

@Service
public class ProductService implements IProductService {


    private final RestTemplateBuilder builder;

    @Autowired
    public ProductService(RestTemplateBuilder builder) {
        this.builder = builder;
    }
    @Override
    public Product getProductById(Long productId) {
        if (Objects.isNull(productId)) {
            throw new ProductNotFoundException("Product id is invalid.");
        }
        RestTemplate restTemplate = builder.build();
        ResponseEntity<FakeStoreProductResponse> dto =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                        FakeStoreProductResponse.class, productId);

        return ProductMapper.getProductFromFakeStoreResponse(dto.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponse[] response =
                builder.build().getForEntity("https://fakestoreapi.com/products", FakeStoreProductResponse[].class).getBody();
        if(response == null || response.length == 0) {
            throw new ProductNotFoundException("No products available to show!!");
        }
        return ProductMapper.getProductsFromFakeStoreResponse(response);
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = builder.build();
        HttpEntity<Product> requestEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductResponse> responseEntity = restTemplate.postForEntity("http://fakestoreapi.com/products", requestEntity,
                FakeStoreProductResponse.class);
        return ProductMapper.getProductFromFakeStoreResponse(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Product existingProduct = getProductById(productId);
        if(existingProduct == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        FakeStoreProductResponse requestEntity = new FakeStoreProductResponse();
        requestEntity.setTitle(existingProduct.getTitle());
        requestEntity.setCategory(existingProduct.getCategory());
        requestEntity.setDescription(existingProduct.getDescription());
        ResponseEntity<FakeStoreProductResponse> responseEntity = putForEntity(HttpMethod.PUT, "http://fakestoreapi.com/products/{productId}",
                requestEntity, FakeStoreProductResponse.class, productId);
        //return responseEntity.getBody();
        return ProductMapper.getProductFromFakeStoreResponse(Objects.requireNonNull(responseEntity.getBody()));
    }

}

