package com.stockmanager.stockmanager.service;


import com.stockmanager.stockmanager.dto.CreateProductDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.mapper.ProductMapper;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.model.Product;
import com.stockmanager.stockmanager.repository.CategoryRepository;
import com.stockmanager.stockmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Transactional
    public ProductDTO createProduct(CreateProductDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Les données sont manquantes");

        // On récupère la catégorie à partir de son ID
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'id: " + dto.getCategoryId()));


        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category); // on assigne la catégorie récupérée
        product.setActive(dto.getActive());
        product.setManufacturer(dto.getManufacturer());
        product.setOutOfStock(dto.getOutOfStock());
        product.setDetails(dto.getDetails());
        product.setTotalIncome(dto.getTotalIncome());
        product.setTotalCharges(dto.getTotalCharges());
        product.setImage(dto.getImage());

        // Sauvegarde et mapping en DTO
        Product saved = productRepository.save(product);
        return ProductMapper.toDto(saved);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            return ProductMapper.toDto(product); // Convert the Product to ProductDTO using the ProductMapper
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        productRepository.delete(product);
    }
}
