package ru.glazunov.numberDetectionJava.exception;

public class TooMuchFiles extends RuntimeException{
    public TooMuchFiles(String message) {
        super(message);
    }
}
