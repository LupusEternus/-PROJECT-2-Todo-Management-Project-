package com.wilk.todo.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
