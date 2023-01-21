package com.example.usm.utility;

import com.example.usm.enums.UserType;

public class UserUtils {
    public static UserType getUserType(String serialNumber){
        char lastDigit = serialNumber.charAt(serialNumber.length()-1);

        return (Character.isDigit(lastDigit)? UserType.Anonymous : UserType.Normal);
    }
}
