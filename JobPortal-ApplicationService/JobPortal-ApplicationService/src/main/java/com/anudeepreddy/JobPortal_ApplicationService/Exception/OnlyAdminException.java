package com.anudeepreddy.JobPortal_ApplicationService.Exception;

public class OnlyAdminException extends RuntimeException{
    public OnlyAdminException(String message) {
        super(message);
    }
}
