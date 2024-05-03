package dev.nikhil.projectservice.exceptions;

public class NoProductPresentException extends RuntimeException{

    public NoProductPresentException(String message) {
        super(message);
    }
}
