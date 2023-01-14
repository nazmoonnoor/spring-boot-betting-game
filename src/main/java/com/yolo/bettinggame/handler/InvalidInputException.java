package com.yolo.bettinggame.handler;

public class InvalidInputException extends RuntimeException{

    private int errorCode;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
