package com.stockmanager.stockmanager.mapper;

import com.stockmanager.stockmanager.dto.ProductSaleResponseDTO;
import com.stockmanager.stockmanager.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductSaleMapper {

    //Mapper avec MapStruct (pour la vente) afin de passer de DTO à Entity et vice versa

    ProductSaleMapper INSTANCE = Mappers.getMapper(ProductSaleMapper.class);

    @Mapping(source = "title", target = "productTitle")
    @Mapping(target = "quantitySold", ignore = true) // On l'ajoutera manuellement
    @Mapping(target = "remainingQuantity", source = "quantity")
    @Mapping(target = "saleIncome", ignore = true) // On l'ajoutera manuellement
    ProductSaleResponseDTO toProductSaleResponseDTO(Product product);

}
