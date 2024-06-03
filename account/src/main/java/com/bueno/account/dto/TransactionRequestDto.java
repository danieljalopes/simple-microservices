package com.bueno.account.dto;

import com.bueno.account.entity.enums.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TransactionRequestDto(
        @NotEmpty(message = "Description is required")
        String description,
        @Positive(message = "Value must be greater than zero")
        Long value,
        @NotNull(message = "Type is required")
        TransactionType type) {
}
