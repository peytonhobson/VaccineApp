package com.VaccineApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.VaccineApp.enumeration.PersonStatus;

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
    @Column(nullable = false)
    private PersonStatus vaccineStatus;
    private String password;
    private static int currentId = 0; // Could change this to implement usernames

    public Person(String name, String email, String phone, String imageUrl, PersonStatus vaccineStatus, String password)
            throws NoSuchAlgorithmException {
        this.id = currentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.vaccineStatus = vaccineStatus;
        this.password = hashPassword(password);
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

    public PersonStatus getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(PersonStatus vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    /**
     * Algorithm for using sha-512 for hasing password.
     * Code used from
     * @param password
     * @return
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
                ", vaccineStatus=" + vaccineStatus +
                '}';
    }
}