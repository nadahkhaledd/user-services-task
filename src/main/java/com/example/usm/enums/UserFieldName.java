package com.example.usm.enums;

public enum UserFieldName {
    SerialNumber("serial number"), PhoneNumber("phone number");

    private String label;

    UserFieldName(String label) {
        this.label = label;
    }
}
