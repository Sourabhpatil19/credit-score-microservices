package com.project.user_service.expection;

public class InvalidTokenException  extends RuntimeException{
    public InvalidTokenException(String message) {
        super(message);
    }
}
