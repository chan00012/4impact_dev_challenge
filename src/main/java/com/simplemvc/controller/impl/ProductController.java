package com.simplemvc.controller.impl;

import com.simplemvc.controller.ProductApi;
import com.simplemvc.dao.AddProductRequest;
import com.simplemvc.dao.AddProductResponse;
import com.simplemvc.dao.RetrieveProductResponse;
import com.simplemvc.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductApi {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping("/get/all")
    public List<RetrieveProductResponse> getAllProducts() {
        return productService.retrieveAllProducts();
    }

    @Override
    @GetMapping("/get")
    public RetrieveProductResponse getProductById(@RequestParam Long productId) {
        return productService.retrieveByProductId(productId);
    }

    @Override
    @PostMapping("/add")
    public AddProductResponse addProduct(@RequestBody AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }
}
