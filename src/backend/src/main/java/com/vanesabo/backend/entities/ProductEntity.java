package com.vanesabo.backend.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private BigDecimal price;

    @Column
    private Integer availableStock;

    @Column
    private LocalDate lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private AddressEntity supplier;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private AddressEntity image;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, String category, BigDecimal price, Integer availableStock, LocalDate lastUpdateDate, AddressEntity supplier, AddressEntity image) {
        this.id = id;
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
}
