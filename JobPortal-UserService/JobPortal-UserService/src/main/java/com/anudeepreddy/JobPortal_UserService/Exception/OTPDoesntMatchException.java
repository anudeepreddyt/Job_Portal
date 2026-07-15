package com.anudeepreddy.JobPortal_UserService.Exception;

public class OTPDoesntMatchException extends RuntimeException{
    public OTPDoesntMatchException(String message) {
        super(message);
    }
}
