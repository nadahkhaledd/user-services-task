package com.example.usm.domain_primitive;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.*;

@Setter @Getter
public class SerialNumber {
    private static final int VALID_LENGTH = 19;
    private static final String VALID_PATTERN = "^([0-9]{4}-){3}([0-9]{3})[a-zA-Z0-9]$";

    private final String value;

    public SerialNumber(final String value){
        notNull(value);
        notBlank(value);
        Validate.isTrue(value.length() == VALID_LENGTH, "Length of serial number must be 19 e.g.(xxxx-xxxx-xxxx-xxxx)");
        matchesPattern(value, VALID_PATTERN, "Invalid serial number pattern e.g.(xxxx-xxxx-xxxx-xxxx)");
        this.value = value;

    }

    public String value(){
        return value;
    }
}
