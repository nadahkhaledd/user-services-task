package com.example.usm.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("user doesn't exist");
    }
    public UserNotFoundException(FieldName fieldName) {
        super(String.format("Could not find user with {} provided ", fieldName));
    }

}
