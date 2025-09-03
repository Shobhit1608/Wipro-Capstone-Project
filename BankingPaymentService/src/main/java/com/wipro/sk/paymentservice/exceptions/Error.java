package com.wipro.sk.paymentservice.exceptions;

public class Error {

    private String message;
    private String details;

    public Error(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }


    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
