package com.project.manager.exceptions;

public class DifferentPasswordException extends RuntimeException {
    public DifferentPasswordException(String s) {
        super(s);
    }
}
