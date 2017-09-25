package com.dopsonbr.benchmark.springsync157;

import org.springframework.http.HttpStatus;

public class OrderException extends Exception{

    private String appMessage;
    private HttpStatus httpStatus;

    public OrderException(String appMessage, String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.appMessage = appMessage;
        this.httpStatus = httpStatus;
    }
}
