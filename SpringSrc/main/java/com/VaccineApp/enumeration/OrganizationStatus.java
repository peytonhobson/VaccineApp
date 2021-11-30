package com.VaccineApp.enumeration;

/**
 * Enumeration for the vaccination status.
 * Ensures that there can only be two statuses
 * for an organization or an error code will be returned.
 */
public enum OrganizationStatus {
    REQUIRED("REQUIRED"),
    NOT_REQUIRED("NOT_REQUIRED");

    private final String status;

    OrganizationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}