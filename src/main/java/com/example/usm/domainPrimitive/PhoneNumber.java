package com.example.usm.domainPrimitive;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.*;

@Setter @Getter
public class PhoneNumber {

    private static final int MINIMUM_LENGTH = 11;
    private static final int MAXIMUM_LENGTH = 14;

    private static final String VALID_PATTERN = "^(([+]|00)[1-9])?(0[1-9]\\d{9})$";

    private final String value;

    public PhoneNumber(String value){
        notNull(value);
        notBlank(value);
        inclusiveBetween(MINIMUM_LENGTH, MAXIMUM_LENGTH, value.length(), "phone number length must be between 11 and 14 e.g.(00/+2xxxxxxxxxxx)");
        matchesPattern(value, VALID_PATTERN, "Invalid phone number pattern e.g.(00/+2xxxxxxxxxxx)");

        this.value = value;
    }


    public String value(){
        return value;
    }
}
