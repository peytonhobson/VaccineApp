package com.VaccineApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.VaccineApp.enumeration.Status;

@Entity
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String imageUrl;
    private Status vaccineRequirement;
    private String password;
    private static int currentId = 0;

    public Organization(String name, String email, String phone, String imageUrl, Status vaccineRequirement, String password)
            throws NoSuchAlgorithmException {
        this.id = currentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.vaccineRequirement = vaccineRequirement;
        this.password = hashPassword(password);
        currentId++;
    }

    public Organization() {}

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

    public Status getVaccineRequirement() {
        return vaccineRequirement;
    }

    public void setVaccineRequirement(Status vaccineRequirement) {
        this.vaccineRequirement = vaccineRequirement;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    /**
     * Algorithm for using sha-512 for hashing password.
     * Code used from
     * @param password  input actual password
     * @return String as hashed password
     */
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(hashedPassword);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", vaccineStatus=" + vaccineRequirement +
                '}';
    }
}