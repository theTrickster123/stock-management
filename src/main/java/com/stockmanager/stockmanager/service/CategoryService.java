package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.dto.CategoryDTO;
import com.stockmanager.stockmanager.mapper.CategoryMapper;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category categoryToUpdate = category.get();
            categoryToUpdate.setDescription(categoryDTO.getDescription());
            categoryToUpdate.setTitle(categoryDTO.getTitle());
            categoryRepository.save(categoryToUpdate);
            return CategoryMapper.toDto(categoryToUpdate);
        }
        throw new RuntimeException("Category not found with id: " + id);

    }
}
