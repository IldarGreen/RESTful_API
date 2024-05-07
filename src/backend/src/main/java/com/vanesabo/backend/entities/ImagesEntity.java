package com.vanesabo.backend.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "images")
public class ImagesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    public ImagesEntity() {
    }

    public ImagesEntity(UUID id, List<ProductEntity> products) {
        this.id = id;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagesEntity that = (ImagesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }

    @Override
    public String toString() {
        return "ImagesEntity{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
