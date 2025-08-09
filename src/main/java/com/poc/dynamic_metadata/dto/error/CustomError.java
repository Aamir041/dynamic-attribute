package com.poc.dynamic_metadata.dto.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomError {
    private HttpStatus errorCode;
    private String errorMessage;
}
