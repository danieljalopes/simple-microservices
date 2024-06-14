package com.bueno.account.dto;

public record AccountDto(
        Long id,
        String name,
        Long limite,
        Long saldo) {
}
