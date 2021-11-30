package com.VaccineApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.VaccineApp.enumeration.OrganizationStatus;
import com.VaccineApp.enumeration.PersonStatus;
import com.VaccineApp.repo.OrganizationRepo;

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
    private OrganizationStatus vaccineRequirement;
    private String password;
    private static int currentId = 0;

    public Organization(String name, String email, String phone, String imageUrl, OrganizationStatus vaccineRequirement, String password)
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

    /**
     * Getter function for email
     * @return  Email as string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email Email address of org
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter function for phone number
     * @return  Phone number as string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter function for phone number
     * @param phone Phone number of org
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter function for imageUrl
     * @return  Image URL as string
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setter function for imageUrl
     * @param imageUrl  A URL for the image of the org
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Getter function for vaccineRequirements
     * @return  Vaccine requirement as a status (defined in enum)
     */
    public OrganizationStatus getVaccineRequirement() {
        return vaccineRequirement;
    }

    /**
     * Setter function for vaccine requirements
     * @param vaccineRequirement    Vaccine requirement of organization
     */
    public void setVaccineRequirement(OrganizationStatus vaccineRequirement) {
        this.vaccineRequirement = vaccineRequirement;
    }

    /**
     * Getter function for user hashed password
     * @return  password as string;
     */
    public String getPassword() { return password; }

    /**
     * Setter function for password
     * @param password Password for user
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Algorithm for using sha-512 for hashing password.
     * Code used from https://www.baeldung.com/java-password-hashing
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

    /**
     * Regular toString() function that includes every
     * class variable except for password
     * @return  JSON string of class variables and values
     */
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