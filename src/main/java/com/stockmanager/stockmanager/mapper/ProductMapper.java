package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.CreateProductDTO;
import com.stockmanager.stockmanager.dto.ProductDTO;
import com.stockmanager.stockmanager.dto.UpdateProductDTO;
import com.stockmanager.stockmanager.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    CreateProductDTO toCreateProductDTO(Product product);

    @Mapping(source = "id", target = "id")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "id", target = "id")
    Product toProduct(CreateProductDTO createProductDTO);

    // Map pour UpdateProductDTO, uniquement les champs pertinents
    @Mapping(target = "id", ignore = true)
    void updateProductFromDTO(UpdateProductDTO updateProductDTO, @MappingTarget Product product);

    List<ProductDTO> toDTOList(List<Product> products); // Convertir une liste d'entités en DTO
    List<Product> toEntityList(List<ProductDTO> dtos);   // Convertir une liste de DTOs en entités
}
