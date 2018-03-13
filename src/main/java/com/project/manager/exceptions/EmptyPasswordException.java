package com.project.manager.exceptions;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException(String s) {
        super(s);
    }
}
