package com.VaccineApp.enumeration;

public enum Status {
    VACCINATED("VACCINATED"),
    NOT_VACCINATED("NOT_VACCINATED");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}