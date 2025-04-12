package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
