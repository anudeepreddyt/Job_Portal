package com.anudeepreddy.JobPortal_UserService.Exception;

public class MailExistException extends RuntimeException{
    public MailExistException(String message) {
        super(message);
    }
}
