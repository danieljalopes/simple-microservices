package com.bueno.account.dto;

public record AccountDto(
        String id,
        String name,
        Long limite,
        Long saldo) {
}
