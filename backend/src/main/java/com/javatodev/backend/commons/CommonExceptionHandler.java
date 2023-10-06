package com.javatodev.backend.commons;

import com.javatodev.backend.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleuserNotFound(UserNotFoundException e)
    {
        ApiExceptionModel apiExceptionModel=new ApiExceptionModel(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiExceptionModel,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {DatabaseUtilException.class})
    public ResponseEntity<Object> databaseAlreadyFilling(DatabaseUtilException e)
    {
        ApiExceptionModel apiExceptionModel=new ApiExceptionModel(
                e.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiExceptionModel,HttpStatus.NOT_FOUND);
    }
}
