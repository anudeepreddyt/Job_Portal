package com.anudeepreddy.JobPortal_ApplicationService.Exception;

public class UserAppliedForJobAlreadyException extends RuntimeException {
    public UserAppliedForJobAlreadyException(String message) {
        super(message);
    }
}
