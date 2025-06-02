package com.personal.productservice.controller;

import com.personal.productservice.dto.ProductRequestDTO;
import com.personal.productservice.dto.ProductResponseDTO;
import com.personal.productservice.mapper.ProductMapper;
import com.personal.productservice.models.Product;
import com.personal.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Qualifier("fakeStoreProductService")
    IProductService fakeStoreProductService;

    @Qualifier("inStoreProductService")
    IProductService inStoreProductService;

    private final ProductMapper productMapper;
    public ProductController(@Qualifier("fakeStoreProductService") IProductService fakeStoreProductService,
                             @Qualifier("inStoreProductService") IProductService inStoreProductService, ProductMapper productMapper) {

        this.fakeStoreProductService = fakeStoreProductService;
        this.inStoreProductService = inStoreProductService;
        this.productMapper = productMapper;
    }

    private IProductService getService(String serviceType) {
        if(serviceType.equalsIgnoreCase("fakeStore")) {
            return fakeStoreProductService;
        }
        else {
            return inStoreProductService;
        }
    }

    //GET getAllProducts() "/"
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestParam(name = "serviceType", required = false,
            defaultValue = "inStore") String serviceType) {
        IProductService productService = getService(serviceType);
        List<Product> getAllProducts = productService.getAllProducts();
        List<ProductResponseDTO> products = ProductMapper.getProductResponseDTOListFromProduct(getAllProducts);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO ProductRequestDTO,
                                                        @RequestParam(name = "serviceType", required = false, defaultValue = "inStore") String serviceType) {
        IProductService productService = getService(serviceType);
        Product product = productService.createProduct(productMapper.getProductFromCreateRequestDTO(ProductRequestDTO));
        ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> getProductById(@PathVariable("productId") Long productId,
                                              @RequestParam(name = "serviceType", required = false, defaultValue = "inStore") String serviceType,
                                              @RequestHeader MultiValueMap<String, String> headers) {
        IProductService productService = getService(serviceType);
        Product data = productService.getProductById(productId);
        ProductResponseDTO productResponseDTO = ProductMapper.getProductResponseDTOFromProduct(data);
        //MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        //headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> updateProduct(@PathVariable("productId") Long productId,
                                                        @RequestParam(name = "serviceType", required = false, defaultValue = "inStore") String serviceType,
                                                        @RequestBody ProductRequestDTO ProductRequestDTO) {
        IProductService productService = getService(serviceType);
        Product product = productService.updateProduct(productId, productMapper.getProductFromCreateRequestDTO(ProductRequestDTO));
        ProductResponseDTO productResponse = ProductMapper.getProductResponseDTOFromProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
