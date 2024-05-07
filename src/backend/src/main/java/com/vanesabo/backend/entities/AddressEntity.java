package com.vanesabo.backend.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String street;

    @OneToMany(mappedBy = "address_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientEntity> clients;

    @OneToMany(mappedBy = "address_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierEntity> suppliers;

    public AddressEntity() {
    }

    public AddressEntity(UUID id, String country, String city, String street) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street);
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
