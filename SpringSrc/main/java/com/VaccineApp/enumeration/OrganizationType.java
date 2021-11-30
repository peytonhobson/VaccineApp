package com.VaccineApp.enumeration;

public enum OrganizationType {
    GOVERNMENT("GOVERNMENT"),
    AIRLINE("AIRLINE"),
    VENUE("VENUE"),
    RESTAURANT("RESTAURANT"),
    STORE("STORE");


    private final String type;

    OrganizationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
