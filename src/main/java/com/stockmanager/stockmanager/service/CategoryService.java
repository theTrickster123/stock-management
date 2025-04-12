package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.CategoryDTO;
import com.stockmanager.stockmanager.mapper.CategoryMapper;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category appUser) {
        return categoryRepository.save(appUser);
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            return CategoryMapper.toDto(category.get()); // Mapping entity to DTO
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
