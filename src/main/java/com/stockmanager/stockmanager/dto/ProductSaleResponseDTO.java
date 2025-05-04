package com.stockmanager.stockmanager.dto;



import java.math.BigDecimal;

//Classe dédié à ce qui sera affiché au niveau du response

//Getters, setters, toString etc generated with Lombok
public class ProductSaleResponseDTO {
    private String productTitle;
    private int quantitySold;
    private int remainingQuantity;
    private BigDecimal saleIncome;
    private BigDecimal totalIncome;
    private BigDecimal totalCharges;
    private boolean isOutOfStock;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public BigDecimal getSaleIncome() {
        return saleIncome;
    }

    public void setSaleIncome(BigDecimal saleIncome) {
        this.saleIncome = saleIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(BigDecimal totalCharges) {
        this.totalCharges = totalCharges;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        isOutOfStock = outOfStock;
    }
}
