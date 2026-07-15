package com.anudeepreddy.JobPortal_UserService.Exception;

public class OTPExpirationException extends RuntimeException{
    public OTPExpirationException(String message) {
        super(message);
    }
}
