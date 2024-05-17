package com.vanesabo.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer availableStock;
    private LocalDate lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImagesEntity image;

    public ProductEntity() {
    }

    public ProductEntity(String name, String category, BigDecimal price, Integer availableStock, LocalDate lastUpdateDate, SupplierEntity supplier) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availableStock = availableStock;
        this.lastUpdateDate = lastUpdateDate;
        this.supplier = supplier;
    }

    public ProductEntity(String name, String category, BigDecimal price, Integer availableStock, LocalDate lastUpdateDate, SupplierEntity supplier, ImagesEntity image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availableStock = availableStock;
        this.lastUpdateDate = lastUpdateDate;
        this.supplier = supplier;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(price, that.price) && Objects.equals(availableStock, that.availableStock) && Objects.equals(lastUpdateDate, that.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price, availableStock, lastUpdateDate);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", availableStock=" + availableStock +
                ", lastUpdateDate=" + lastUpdateDate +
                ", supplier=" + supplier +
                ", image=" + image +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public ImagesEntity getImage() {
        return image;
    }

    public void setImage(ImagesEntity image) {
        this.image = image;
    }
}
