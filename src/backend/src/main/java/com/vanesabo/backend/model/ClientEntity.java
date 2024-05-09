package com.vanesabo.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client_name;
    private String client_surname;
    private String birthday;
    private String gender;
    private String registration_date;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public ClientEntity() {
    }

    public ClientEntity(String client_name, String client_surname, String birthday, String gender, String registration_date, AddressEntity address) {
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.birthday = birthday;
        this.gender = gender;
        this.registration_date = registration_date;
        this.address = address;
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
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
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

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
