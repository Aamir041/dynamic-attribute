package com.poc.dynamic_metadata.dto;

import lombok.Data;

import java.util.Map;

@Data
public class UserResponse {
    private String username;
    private Map<String, Object> userMetadata;
}
