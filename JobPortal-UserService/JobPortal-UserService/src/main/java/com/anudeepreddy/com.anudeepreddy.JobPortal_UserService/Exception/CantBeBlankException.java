package com.anudeepreddy.JobPortal_UserService.Exception;

public class CantBeBlankException extends IllegalArgumentException {
    public CantBeBlankException(String message) {
        super(message);
    }
}
