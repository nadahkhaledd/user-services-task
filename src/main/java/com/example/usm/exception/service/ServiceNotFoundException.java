package com.example.usm.exception.service;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException(){
        super("Service doesn't exist");
    }
}
