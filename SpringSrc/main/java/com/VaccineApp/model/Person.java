package com.VaccineApp.model;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Class that represents people as an entity in
 * a relational database.
 */
public class Person extends User {

    /**
     * Status if person requires being vaccinated or not
     */
    private boolean vaccine;

    private Date test;



    public Person(String username, String name, String email, String phone, String profilePic, boolean vaccine, String password, Date test)
            throws NoSuchAlgorithmException {
        super(username,name,email,phone, profilePic, password);
        this.vaccine = vaccine;
        this.test = test;

    }

    /**
     * Empty Constructor of person
     */
    public Person() {}




}