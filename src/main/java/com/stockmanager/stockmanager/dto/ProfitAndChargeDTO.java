package com.stockmanager.stockmanager.dto;

import java.math.BigDecimal;

public class ProfitAndChargeDTO {

    private BigDecimal totalRevenue;
    private BigDecimal totalCost;
    private BigDecimal profit;

    public ProfitAndChargeDTO(BigDecimal totalRevenue, BigDecimal totalCost, BigDecimal profit) {
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.profit = profit;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
