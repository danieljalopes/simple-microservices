package com.bueno.customer.service;

import com.bueno.customer.dto.TransactionRequestDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TransactionGeneratorService {

    public static final String DEBIT_OPERATION = "D";
    public static final String CREDIT_OPERATION = "C";
    public static final String CREDIT_DESCRIPTION = "Receipt of work hours in %s.";
    public static final String DEBIT_DESCRIPTION = "Payment for beer %s, cheers!";
    private final Faker faker;

    public TransactionGeneratorService() {
        this.faker = new Faker(new Locale("pt-BR"));
    }

    public TransactionRequestDto generateTransaction() {
        String operation = chooseOperation();
        String description = generateDescriptionText(operation);
        Long value = faker.number().numberBetween(1L, 1000L);

        return TransactionRequestDto.builder()
                                    .description(description)
                                    .value(value)
                                    .type(operation)
                                    .build();
    }

    private String chooseOperation() {
        return faker.options().option(DEBIT_OPERATION, CREDIT_OPERATION);
    }

    private String generateDescriptionText(String operation) {
        if (CREDIT_OPERATION.equals(operation)) {
            return String.format(CREDIT_DESCRIPTION, faker.programmingLanguage().name());
        }
        return String.format(DEBIT_DESCRIPTION, faker.beer().name());
    }
}
