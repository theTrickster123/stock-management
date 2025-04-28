package com.stockmanager.stockmanager.dto;

import lombok.Data;

import java.math.BigDecimal;

//Classe dédié à ce qui sera affiché au niveau du response

@Data //Getters, setters, toString etc generated with Lombok
public class ProductSaleResponseDTO {
    private String productTitle;
    private int quantitySold;
    private int remainingQuantity;
    private BigDecimal saleIncome;
    private BigDecimal totalIncome;
    private BigDecimal totalCharges;
    private boolean isOutOfStock;


}
