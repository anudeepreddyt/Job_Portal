package com.anudeepreddy.JobPortal_ApplicationService.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
