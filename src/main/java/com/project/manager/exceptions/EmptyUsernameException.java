package com.project.manager.exceptions;

public class EmptyUsernameException extends RuntimeException{
    public EmptyUsernameException(String s) {
        super(s);
    }
}
