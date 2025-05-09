package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitleIgnoreCase(String title);



    @Query("SELECT p FROM Product p WHERE p.totalIncome <= 0.00")
    List<Product> findProductsWithNoIncome();

    Optional<Product> findProductByTitle(String title);


}
