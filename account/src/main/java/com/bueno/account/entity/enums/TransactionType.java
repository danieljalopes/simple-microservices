package com.bueno.account.entity.enums;

public enum TransactionType {
    C("CREDIT"),
    D("DEBIT");
    private final String description;


    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
