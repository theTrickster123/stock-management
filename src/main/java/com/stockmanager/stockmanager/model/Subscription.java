package com.stockmanager.stockmanager.model;

import com.stockmanager.stockmanager.enums.Plan;
import com.stockmanager.stockmanager.enums.Role;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="subscription")
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPaid = BigDecimal.ZERO; // Default value 0.

    @Column(nullable = false,length = 15)
    private Plan plan;

    @Column(name="is_active",nullable = false)
    private boolean isActive=true;

    @OneToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", nullable = false)
    private AppUser appUser;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(name="expired_at",nullable = false)
    private LocalDate expiredAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Subscription(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, BigDecimal price, BigDecimal totalPaid, Plan plan, boolean isActive, AppUser appUser, String description, LocalDate expiredAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.price = price;
        this.totalPaid = totalPaid;
        this.plan = plan;
        this.isActive = isActive;
        this.appUser = appUser;
        this.description = description;
        this.expiredAt = expiredAt;
    }

    public Subscription() {

    }

    public UUID getId() {
        return id;
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", price=" + price +
                ", totalPaid=" + totalPaid +
                ", plan=" + plan +
                ", isActive=" + isActive +
                ", appUser=" + appUser +
                ", description='" + description + '\'' +
                ", expiredAt=" + expiredAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return isActive == that.isActive && Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(price, that.price) && Objects.equals(totalPaid, that.totalPaid) && plan == that.plan && Objects.equals(appUser, that.appUser) && Objects.equals(description, that.description) && Objects.equals(expiredAt, that.expiredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, price, totalPaid, plan, isActive, appUser, description, expiredAt);
    }
}
