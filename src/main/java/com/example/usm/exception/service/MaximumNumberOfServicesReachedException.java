package com.example.usm.exception.service;

public class MaximumNumberOfServicesReachedException extends RuntimeException{

    public MaximumNumberOfServicesReachedException(){
        super("can't add more services to user, reached max amount");
    }
}
