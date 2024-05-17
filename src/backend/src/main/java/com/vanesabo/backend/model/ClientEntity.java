package com.vanesabo.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String clientSurname;
    private LocalDate birthday;
    private String gender;
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public ClientEntity() {
    }

    public ClientEntity(String client_name, String client_surname, LocalDate birthday, String gender, LocalDate registration_date, AddressEntity address) {
        this.clientName = client_name;
        this.clientSurname = client_surname;
        this.birthday = birthday;
        this.gender = gender;
        this.registrationDate = registration_date;
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", client_name='" + clientName + '\'' +
                ", client_surname='" + clientSurname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", registration_date='" + registrationDate + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String client_name) {
        this.clientName = client_name;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String client_surname) {
        this.clientSurname = client_surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registration_date) {
        this.registrationDate = registration_date;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
