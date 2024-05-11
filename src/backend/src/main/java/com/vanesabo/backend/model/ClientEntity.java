package com.vanesabo.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @NotBlank///////////////////////////////////////////////////////
    private String clientName;
    private String clientSurname;
    private String birthday;
    private String gender;
    private String registrationDate;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public ClientEntity() {
    }

    public ClientEntity(String client_name, String client_surname, String birthday, String gender, String registration_date, AddressEntity address) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registration_date) {
        this.registrationDate = registration_date;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
