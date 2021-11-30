package model;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import com.VaccineApp.enumeration.OrganizationStatus;
import com.VaccineApp.enumeration.OrganizationType;

/**
 * Class that represents organization as an entity in
 * a relational database.
 */
public class Organization extends User {

    /**
     * Status if organization requires being vaccinated or not
     */
    private boolean vaccine;

    /**
     * Status if organization requires being masked or not
     */
    private boolean mask;

    /**
     * Status if organization requires being tested or not
     */
    private boolean test;

    /**
     * Type of organization as defined by enum
     */
    private OrganizationType organizationType;


    public Organization(String username, String name, String email, String phone, String profilePic, String password,
                        boolean vaccine, boolean mask, boolean test, OrganizationType organizationType)
            throws NoSuchAlgorithmException {
        super(username, name, email, phone, profilePic, password);
        this.vaccine = vaccine;
        this.mask = mask;
        this.test = test;
        this.organizationType = organizationType;
    }

    /**
     * Empty constructor for organization
     */
    public Organization() {}

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isMask() {
        return mask;
    }

    public void setMask(boolean mask) {
        this.mask = mask;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", vaccine=" + vaccine +
                ", mask=" + mask +
                ", test=" + test +
                ", organizationType=" + organizationType +
                '}';
    }
}