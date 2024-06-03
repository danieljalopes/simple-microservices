package com.bueno.account.dto;

import java.time.LocalDateTime;

public record TransactionDto(
        String id,
        String value,
        String type,
        String description,
        LocalDateTime createdAt,
        AccountDto account) {
}
