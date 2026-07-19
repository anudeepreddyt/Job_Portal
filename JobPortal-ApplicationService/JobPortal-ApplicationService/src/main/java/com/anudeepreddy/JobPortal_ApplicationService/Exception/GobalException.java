package com.anudeepreddy.JobPortal_ApplicationService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GobalException {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobNotFoundException(JobNotFoundException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAppliedForJobAlreadyException.class)
    public ResponseEntity<ErrorResponse> handleUserAppliedForJobAlreadyException(UserAppliedForJobAlreadyException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(WrongUserException.class)
    public ResponseEntity<ErrorResponse> handleWrongUserException(WrongUserException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(OnlyAdminException.class)
    public ResponseEntity<ErrorResponse> handleOnlyAdminException(OnlyAdminException e){
        ErrorResponse error=new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }


}
