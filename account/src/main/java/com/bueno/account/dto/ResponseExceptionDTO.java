package com.bueno.account.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

/**
 * ResponseExceptionDTO is a class representing the data transfer object for exception responses.
 * It contains the timestamp, message, and details of an exception.
 */
@JsonPropertyOrder(value = {"timestamp", "message", "details"})
public record ResponseExceptionDTO(LocalDateTime timestamp, String message, String details) {
}
