package com.personal.productservice.controller;

import com.personal.productservice.dto.ProductRequestDTO;
import com.personal.productservice.dto.ProductResponseDTO;
import com.personal.productservice.mapper.ProductMapper;
import com.personal.productservice.models.Product;
import com.personal.productservice.service.IProductService;
import com.personal.productservice.service.ProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //GET getAllProducts() "/"
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> getAllProducts = productService.getAllProducts();
        List<ProductResponseDTO> products = ProductMapper.getProductResponseDTOListFromProduct(getAllProducts);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO ProductRequestDTO) {
        Product product = productService.createProduct(ProductMapper.getProductFromCreateRequestDTO(ProductRequestDTO));
        ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> getProductById(@PathVariable("productId") Long productId,
                                              @RequestHeader MultiValueMap<String, String> headers) {
        Product data = productService.getProductById(productId);
        ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(data);
        //MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        //headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> updateProduct(@PathVariable("productId") Long productId,
                                                        @RequestBody ProductRequestDTO ProductRequestDTO) {
        Product product = productService.updateProduct(productId, ProductMapper.getProductFromCreateRequestDTO(ProductRequestDTO));
        ProductResponseDTO productResponse = ProductMapper.getProductResponseDTOFromProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
