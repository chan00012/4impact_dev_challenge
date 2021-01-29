package com.simplemvc.service.impl;

import com.simplemvc.dao.AddProductRequest;
import com.simplemvc.dao.AddProductResponse;
import com.simplemvc.dao.ProductVariation;
import com.simplemvc.dao.RetrieveProductResponse;
import com.simplemvc.model.Product;
import com.simplemvc.model.Variation;
import com.simplemvc.repository.ProductRepository;
import com.simplemvc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {

        Product product = productRepository.findByProductName(addProductRequest.getProductName());
        AddProductResponse addProductResponse = new AddProductResponse();

        if (!Objects.isNull(product)) {
            logger.error("Product already exist.");
            addProductResponse.setStatus("FAILED");
            addProductResponse.setDescription("Product already exist.");
            return addProductResponse;
        }

        Product incomingProduct = new Product();
        incomingProduct.setProductName(addProductRequest.getProductName());
        incomingProduct.setActive(addProductRequest.isActive());
        incomingProduct.setProductDescription(addProductRequest.getProductDescription());

        List<Variation> variations = addProductRequest.getVariations().stream()
                .map(pv -> new Variation(pv.getVariationName(), pv.getRemainingStock(), incomingProduct))
                .collect(Collectors.toList());

        incomingProduct.setVariations(variations);
        productRepository.save(incomingProduct);

        logger.info("Product added successfully.");
        addProductResponse.setStatus("SUCCESS");
        addProductResponse.setDescription("Product added successfully.");

        return addProductResponse;
    }

    @Override
    public List<RetrieveProductResponse> retrieveAllProducts() {
        List<Product> products = productRepository.findAll();

        logger.info("Retrieve all products.");
        return products.stream()
                .map(product -> {
                    RetrieveProductResponse response = new RetrieveProductResponse();
                    response.setProductId(product.getProductId());
                    response.setProductName(product.getProductName());
                    response.setProductDescription(product.getProductDescription());
                    response.setVariations(product.getVariations().stream()
                            .map(variation -> new ProductVariation(variation.getVariationName(), variation.getRemainingStock()))
                            .collect(Collectors.toList()));
                    return response;
                }).collect(Collectors.toList());
    }

    @Override
    public RetrieveProductResponse retrieveByProductId(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            RetrieveProductResponse response = new RetrieveProductResponse();
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());
            response.setProductDescription(product.getProductDescription());
            response.setVariations(product.getVariations().stream()
                    .map(variation -> new ProductVariation(variation.getVariationName(), variation.getRemainingStock()))
                    .collect(Collectors.toList()));

            logger.info("Product found with id: {}.", productId);
            return response;
        } else {
            logger.error("Product not found.");
            return new RetrieveProductResponse();
        }
    }
}
