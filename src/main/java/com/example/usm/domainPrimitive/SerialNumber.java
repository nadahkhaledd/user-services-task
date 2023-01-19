package com.example.usm.domainPrimitive;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.*;

public class SerialNumber {
    private static final int VALID_LENGTH = 19;
    private static final String VALID_PATTERN = "^([0-9]{4}-){3}([0-9]{3})[a-zA-Z0-9]$";

    private final String value;

    public SerialNumber(final String value){
        notNull(value);
        notBlank(value);
        Validate.isTrue(value.length() == VALID_LENGTH);
        matchesPattern(value, VALID_PATTERN, "Invalid serial number pattern e.g.(xxxx-xxxx-xxxx-xxxx)");
        this.value = value;

    }

    public String value(){
        return value;
    }
}
