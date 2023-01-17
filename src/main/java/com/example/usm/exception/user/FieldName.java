package com.example.usm.exception.user;

public enum FieldName {
    SerialNumber("serial number"), PhoneNumber("phone number");

    private String label;

    FieldName(String label) {
        this.label = label;
    }
}
