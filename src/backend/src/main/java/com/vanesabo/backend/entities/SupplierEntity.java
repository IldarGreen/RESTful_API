package com.vanesabo.backend.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @Column
    private String phone_number;

    public SupplierEntity() {
    }

    public SupplierEntity(UUID id, String name, AddressEntity address, String phone_number) {
        this.id = id;
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
}
