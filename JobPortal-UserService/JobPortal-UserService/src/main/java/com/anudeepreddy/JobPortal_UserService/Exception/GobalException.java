package com.anudeepreddy.JobPortal_UserService.Exception;

import com.anudeepreddy.JobPortal_UserService.DTO.ErrorResponseDTO;
import com.anudeepreddy.JobPortal_UserService.Model.Role;
import io.jsonwebtoken.security.Password;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GobalException {


    @ExceptionHandler(MailExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleMailExistException(MailExistException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(PhoneNumExistException.class)
    public ResponseEntity<ErrorResponseDTO> handlePhoneNumExistException(PhoneNumExistException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleCantChangeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRoleCantChangeException(RoleCantChangeException e)
    {
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CantBeBlankException.class)
    public ResponseEntity<ErrorResponseDTO> handleCantBeBlankException(CantBeBlankException e)
    {
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidException(InvalidException e)
    {
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PasswordDoesntMatchException.class)
    public ResponseEntity<ErrorResponseDTO> handlePasswordDoesntMatchException(PasswordDoesntMatchException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OTPDoesntMatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleOTPDoesntMatchException(OTPDoesntMatchException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(OTPExpirationException.class)
    public ResponseEntity<ErrorResponseDTO> handleOTPExpirationException(OTPExpirationException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotAdminException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotAdminException(NotAdminException e){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }
}
