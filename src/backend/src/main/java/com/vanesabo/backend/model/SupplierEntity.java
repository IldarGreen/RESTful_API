package com.vanesabo.backend.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    private String phone_number;

    public SupplierEntity() {
    }

    public SupplierEntity(String name, AddressEntity address, String phone_number) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierEntity that = (SupplierEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone_number, that.phone_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone_number);
    }

    @Override
    public String toString() {
        return "SupplierEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone_number='" + phone_number + '\'' +
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}