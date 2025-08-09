package com.poc.dynamic_metadata.exception;

import java.io.Serial;

public class ServiceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Throwable err){
        super(message,err);
    }
}
