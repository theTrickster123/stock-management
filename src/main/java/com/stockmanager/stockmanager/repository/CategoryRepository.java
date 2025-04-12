package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
