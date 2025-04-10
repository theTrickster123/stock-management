package com.stockmanager.stockmanager.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price; // Use precision and scale for decimal values

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(length = 50, nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Boolean isOutOfStock;

    @Column(columnDefinition = "TEXT", updatable = false, nullable = true)
    private String details;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal totalIncome;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal totalCharges;

    @Lob
    @Column(nullable = true)
    private byte[] image;

    // Getters and Setters

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
