package com.example.usm.exception.user;

import com.example.usm.enums.UserFieldName;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("user doesn't exist");
    }
    public UserNotFoundException(UserFieldName fieldName) {
        super(String.format("Could not find user with %s provided ", fieldName));
    }

}
