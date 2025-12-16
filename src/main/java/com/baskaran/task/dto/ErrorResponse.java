package com.baskaran.task.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
