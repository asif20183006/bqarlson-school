package com.bqarlson.school.exceptions;

public class ServerException extends Exception {

    private final String errorMessage;

    public ServerException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ServerException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }


    public String getSimpleMessage() {
        return "ServerValidationException :: " + this.errorMessage;
    }
}
