package com.anudeepreddy.JobPortal_ApplicationService.Exception;

public class WrongUserException extends RuntimeException{
    public WrongUserException(String message) {
        super(message);
    }
}
