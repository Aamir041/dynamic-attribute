package com.poc.dynamic_metadata.dto;

import com.poc.dynamic_metadata.utils.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class UserRequest {
    @NotNull(message = ErrorMessage.USERNAME_CAN_NOT_BE_NULL)
    private String username;
    private Map<String, Object> userMetadata;
}
