package com.stockmanager.stockmanager.service;

import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
