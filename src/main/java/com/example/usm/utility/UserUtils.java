package com.example.usm.utility;

import com.example.usm.enums.UserType;

public class UserUtils {
    public static UserType getUserType(String serialNumber){
        char lastDigit = serialNumber.charAt(serialNumber.length()-1);

        if(Character.isDigit(lastDigit))
            return UserType.Anonymous;
        else
            return UserType.Normal;
    }
}
