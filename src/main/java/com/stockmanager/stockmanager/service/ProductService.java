package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.model.Product;
import com.stockmanager.stockmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

}
