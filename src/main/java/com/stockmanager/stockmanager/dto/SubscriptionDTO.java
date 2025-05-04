package com.stockmanager.stockmanager.dto;

import com.stockmanager.stockmanager.enums.Plan;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public class SubscriptionDTO {


    private UUID id;
    private String description;
    private BigDecimal price;
    private BigDecimal totalPaid = BigDecimal.ZERO; // Default value
    private Plan plan;
    private boolean isActive = true;
    private LocalDate expiredAt;
    private UUID userId; // 🔥 Au lieu de l’objet AppUser entier

    // ✅ Constructeur vide (requis pour MapStruct et Jackson)
    public SubscriptionDTO() {}

    public SubscriptionDTO(UUID id, String description, BigDecimal price, BigDecimal totalPaid, Plan plan, boolean isActive, LocalDate expiredAt, UUID userId) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.totalPaid = totalPaid;
        this.plan = plan;
        this.isActive = isActive;
        this.expiredAt = expiredAt;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
