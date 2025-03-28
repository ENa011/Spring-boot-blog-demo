package com.cogent.blog.spring.demo.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public BlogApiException(String customMessage, HttpStatus status, String message){
        super(customMessage);
        this.status = status;
        this.message = message;
    }
}

