package com.vanesabo.backend.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String client_name;

    @Column
    private String client_surname;

    @Column
    private String birthday;

    @Column
    private String gender;

    @Column
    private String registration_date;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public ClientEntity() {
    }

    public ClientEntity(UUID id, String client_name, String client_surname, String birthday, String gender, String registration_date, AddressEntity address_id) {
        this.id = id;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.birthday = birthday;
        this.gender = gender;
        this.registration_date = registration_date;
        this.address = address_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(client_name, that.client_name) && Objects.equals(client_surname, that.client_surname) && Objects.equals(birthday, that.birthday) && Objects.equals(gender, that.gender) && Objects.equals(registration_date, that.registration_date) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client_name, client_surname, birthday, gender, registration_date, address);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", client_name='" + client_name + '\'' +
                ", client_surname='" + client_surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", registration_date='" + registration_date + '\'' +
                ", address_id=" + address +
                '}';
    }
}
