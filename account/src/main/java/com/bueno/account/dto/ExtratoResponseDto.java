package com.bueno.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonPropertyOrder({"balance_date", "balance", "limit", "last_transactions"})
public record ExtratoResponseDto(
        @JsonProperty("balance")
        Long balance,
        @JsonProperty("balance_date")
        LocalDateTime balanceDate,
        @JsonProperty("limit")
        Long limit,
        List<ItemExtratoResponseDto> lastTransactions
) {
}
