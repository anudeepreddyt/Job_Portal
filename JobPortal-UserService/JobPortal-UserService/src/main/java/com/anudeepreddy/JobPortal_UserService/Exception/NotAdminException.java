package com.anudeepreddy.JobPortal_UserService.Exception;

public class NotAdminException extends RuntimeException{
    public NotAdminException(String message) {
        super(message);
    }
}
