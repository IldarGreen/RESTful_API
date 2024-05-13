package com.vanesabo.backend.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "images")
public class ImagesEntity {
    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private byte[] image;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    public ImagesEntity() {
    }

    public ImagesEntity(byte[] image, List<ProductEntity> products) {
        this.image = image;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagesEntity that = (ImagesEntity) o;
        return Objects.equals(id, that.id) && Objects.deepEquals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Arrays.hashCode(image));
    }

    @Override
    public String toString() {
        return "ImagesEntity{" +
                "image=" + Arrays.toString(image) +
                ", id=" + id +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
