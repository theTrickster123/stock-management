package com.stockmanager.stockmanager.dto;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.math.BigDecimal;

public class UpdateProductDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long categoryId; // Référence à une catégorie existante
    private Boolean isActive=true; // Ce champ pourrait être mis à jour selon l'état du produit
    private Boolean isOutOfStock=false; // Ce champ pourrait être mis à jour selon l'état du stock
    private String details;
    private BigDecimal purchasePrice;

    public UpdateProductDTO() {

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getOutOfStock() {
        return isOutOfStock;
    }

    public void setOutOfStock(Boolean outOfStock) {
        isOutOfStock = outOfStock;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
