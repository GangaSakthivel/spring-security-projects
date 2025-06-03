package com.example.OrderManagementSystem.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse() {
        // no-arg constructor for serialization/deserialization
    }

    public ErrorResponse(int status, String message) {
        this.statusCode = status;
        this.message = message;
    }

    // Getters and setters
    public int getStatus() {
        return statusCode;
    }

    public void setStatus(int status) {
        this.statusCode = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
