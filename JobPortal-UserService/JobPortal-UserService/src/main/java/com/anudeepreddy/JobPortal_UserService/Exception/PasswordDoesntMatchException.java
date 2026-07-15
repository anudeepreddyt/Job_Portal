package com.anudeepreddy.JobPortal_UserService.Exception;

public class PasswordDoesntMatchException extends RuntimeException{
    public PasswordDoesntMatchException(String message) {
        super(message);
    }
}
