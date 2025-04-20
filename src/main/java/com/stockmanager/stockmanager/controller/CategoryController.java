package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.dto.CategoryDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.model.Product;
import com.stockmanager.stockmanager.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCatogory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO categoryDTO = categoryService.findById(id);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Not Found if user doesn't exist
        }
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return  new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
       categoryService.deleteCategory(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
}
