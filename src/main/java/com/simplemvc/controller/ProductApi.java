package com.simplemvc.controller;

import com.simplemvc.dao.AddProductRequest;
import com.simplemvc.dao.AddProductResponse;
import com.simplemvc.dao.RetrieveProductResponse;

import java.util.List;

public interface ProductApi {

    List<RetrieveProductResponse> getAllProducts();

    RetrieveProductResponse getProductById(Long productId);

    AddProductResponse addProduct(AddProductRequest addProductRequest);

}
