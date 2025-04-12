package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.CreateProductDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.model.Category;
import com.stockmanager.stockmanager.model.Product;

public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                CategoryMapper.toDto(product.getCategory()), // 🔁 Mapper category aussi
                product.getActive(),
                product.getManufacturer(),
                product.getOutOfStock(),
                product.getDetails(),
                product.getTotalIncome(),
                product.getTotalCharges(),
                product.getImage()
        );
    }

    // Map CreateProductDTO → Product (pour enregistrer en base)
    public static Product toEntity(CreateProductDTO dto, Category category) {
        if (dto == null) return null;

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category); // 💡 on l’injecte depuis le service
        product.setActive(dto.getActive());
        product.setManufacturer(dto.getManufacturer());
        product.setOutOfStock(dto.getOutOfStock());
        product.setDetails(dto.getDetails());
        product.setTotalIncome(dto.getTotalIncome());
        product.setTotalCharges(dto.getTotalCharges());
        product.setImage(dto.getImage());

        return product;

        }


    }

