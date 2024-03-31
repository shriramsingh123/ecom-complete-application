package com.shriram.ecom.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }
}
