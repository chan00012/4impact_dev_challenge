package com.simplemvc.service;

import com.simplemvc.dao.AddProductRequest;
import com.simplemvc.dao.AddProductResponse;
import com.simplemvc.dao.RetrieveProductResponse;

import java.util.List;

public interface ProductService {

    AddProductResponse addProduct(AddProductRequest addProductRequest);

    List<RetrieveProductResponse> retrieveAllProducts();

    RetrieveProductResponse retrieveByProductId(Long productId);
}
