package model;

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

    private String vaccineCard;

    private String id;

    public Person(String username, String name, String email, String phone, String profilePic, boolean vaccine, String password, Date test,
                  String vaccineCard, String id)
            throws NoSuchAlgorithmException {
        super(username,name,email,phone, profilePic, password);
        this.vaccine = vaccine;
        this.test = test;
        this.vaccineCard = vaccineCard;
        this.id = id;
    }

    /**
     * Empty Constructor of person
     */
    public Person() {}

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public Date getTest() {
        return test;
    }

    public void setTest(Date test) {
        this.test = test;
    }

    public String getVaccineCard() {
        return vaccineCard;
    }

    public void setVaccineCard(String vaccineCard) {
        this.vaccineCard = vaccineCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}