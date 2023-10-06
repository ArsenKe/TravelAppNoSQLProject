package com.javatodev.backend.commons;

public class DatabaseUtilException extends RuntimeException{

    public DatabaseUtilException(String message)
    {
        super(message);
    }

    public DatabaseUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}


