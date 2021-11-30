package com.VaccineApp.model;

import javax.persistence.*;
import java.io.Serializable;

import com.VaccineApp.enumeration.Status;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private Status vaccineStatus;
    private static int currentId = 0;

    public Person(String name, String email, String phone, String imageUrl, Status vaccineStatus) {
        this.id = currentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.vaccineStatus = vaccineStatus;
        currentId++;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Status getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(Status vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", vaccineStatus=" + vaccineStatus +
                '}';
    }
}