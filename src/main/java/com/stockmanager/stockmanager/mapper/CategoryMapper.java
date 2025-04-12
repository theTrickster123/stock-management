package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.CategoryDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        if (category == null) return null;

        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription()
        );
    }
}
