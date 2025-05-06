package com.stockmanager.stockmanager.enums;

import java.math.BigDecimal;

public enum Plan {

    BASIC(new BigDecimal("5.99")),
    STANDARD(new BigDecimal("9.99")),
    PREMIUM(new BigDecimal("14.99"));

    private BigDecimal price;

    Plan(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


}
