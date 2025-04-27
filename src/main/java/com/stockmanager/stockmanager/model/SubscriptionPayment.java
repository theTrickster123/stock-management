package com.stockmanager.stockmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="subscription_payment")
public class SubscriptionPayment {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="amount_payed",nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPayed;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id", nullable = false)
    private Subscription subscription;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public SubscriptionPayment(UUID id, BigDecimal amountPayed, LocalDateTime createdAt, LocalDateTime updatedAt, Subscription subscription) {
        this.id = id;
        this.amountPayed = amountPayed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subscription = subscription;
    }

    public SubscriptionPayment() {}

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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "SubscriptionPayment{" +
                "id=" + id +
                ", amountPayed=" + amountPayed +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", subscription=" + subscription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPayment that = (SubscriptionPayment) o;
        return Objects.equals(id, that.id) && Objects.equals(amountPayed, that.amountPayed) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(subscription, that.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountPayed, createdAt, updatedAt, subscription);
    }
}
