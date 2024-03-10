package com.bqarlson.school.exceptions;

public class ClientException extends Exception {
    private final String errorMessage;

    public ClientException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ClientException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }


    public String getSimpleMessage() {
        return "ClientValidationException :: " + this.errorMessage;
    }
}
