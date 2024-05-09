package com.vanesabo.backend.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    private String city;
    private String street;

//    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ClientEntity> clients;
//
//    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<SupplierEntity> suppliers;

    public AddressEntity() {
    }

    public AddressEntity(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

//    public List<ClientEntity> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<ClientEntity> clients) {
//        this.clients = clients;
//    }
//
//    public List<SupplierEntity> getSuppliers() {
//        return suppliers;
//    }
//
//    public void setSuppliers(List<SupplierEntity> suppliers) {
//        this.suppliers = suppliers;
//    }
}
