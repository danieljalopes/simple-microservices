package com.bueno.account.dto;

import com.bueno.account.entity.enums.TransactionType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ItemExtratoResponseDto(
        Long id,
        String description,
        LocalDateTime createdAt,
        TransactionType type,
        Long value
) {
}
