package com.stockmanager.stockmanager.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

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


    //To configure correctly
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Product(Long id, String title, String description, BigDecimal price, Integer quantity, Category category, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive, String manufacturer, Boolean isOutOfStock, String details, BigDecimal totalIncome, BigDecimal totalCharges, byte[] image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
        this.manufacturer = manufacturer;
        this.isOutOfStock = isOutOfStock;
        this.details = details;
        this.totalIncome = totalIncome;
        this.totalCharges = totalCharges;
        this.image = image;
    }

    public Product() {

    }

    public Long getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isActive=" + isActive +
                ", manufacturer='" + manufacturer + '\'' +
                ", isOutOfStock=" + isOutOfStock +
                ", details='" + details + '\'' +
                ", totalIncome=" + totalIncome +
                ", totalCharges=" + totalCharges +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(category, product.category) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt) && Objects.equals(isActive, product.isActive) && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(isOutOfStock, product.isOutOfStock) && Objects.equals(details, product.details) && Objects.equals(totalIncome, product.totalIncome) && Objects.equals(totalCharges, product.totalCharges) && Objects.deepEquals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, quantity, category, createdAt, updatedAt, isActive, manufacturer, isOutOfStock, details, totalIncome, totalCharges, Arrays.hashCode(image));
    }
}
