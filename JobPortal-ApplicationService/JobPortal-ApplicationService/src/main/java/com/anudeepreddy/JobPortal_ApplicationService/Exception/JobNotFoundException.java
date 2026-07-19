package com.anudeepreddy.JobPortal_ApplicationService.Exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {
        super(message);
    }
}
