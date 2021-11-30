package com.VaccineApp.enumeration;

public enum Status {
    VACCINATED("VACCINATED"),
    SERVER_DOWN("NOT_VACCINATED");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}