package com.VaccineApp.enumeration;

/**
 * Enumeration for the vaccination status.
 * Ensures that there can only be two statuses
 * for a person or an error code will be returned.
 */
public enum PersonStatus {
    VACCINATED("VACCINATED"),
    NOT_VACCINATED("NOT_VACCINATED");

    private final String status;

    PersonStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}