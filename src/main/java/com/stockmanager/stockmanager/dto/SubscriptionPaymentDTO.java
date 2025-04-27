package com.stockmanager.stockmanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionPaymentDTO {

    private UUID id;
    private BigDecimal amountPayed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID subscriptionId;

    public SubscriptionPaymentDTO() {}

    public SubscriptionPaymentDTO(UUID id, BigDecimal amountPayed, LocalDateTime createdAt, LocalDateTime updatedAt, UUID subscriptionId) {
        this.id = id;
        this.amountPayed = amountPayed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subscriptionId = subscriptionId;
    }

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(BigDecimal amountPayed) {
        this.amountPayed = amountPayed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
