package com.bueno.customer.dto;

import lombok.Builder;

@Builder
public record TransactionRequestDto(
        String description,
        Long value,
        String type) {
}