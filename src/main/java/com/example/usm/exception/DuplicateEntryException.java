package com.example.usm.exception;

public class DuplicateEntryException extends RuntimeException{

    public DuplicateEntryException(){
        super("Duplicate entry was encountered");
    }
}
