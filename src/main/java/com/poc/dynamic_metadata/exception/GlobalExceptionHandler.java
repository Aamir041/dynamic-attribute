package com.poc.dynamic_metadata.exception;

import com.poc.dynamic_metadata.dto.error.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError handleResourceNotFound(ResourceNotFound resourceNotFound){
        CustomError error = new CustomError();
        error.setErrorCode(HttpStatus.NOT_FOUND);
        error.setErrorMessage(resourceNotFound.getMessage());
        return error;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleServiceException(ServiceException serviceException){
        CustomError error = new CustomError();
        error.setErrorMessage(serviceException.getMessage());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return error;
    }
}
