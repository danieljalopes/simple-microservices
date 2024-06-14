package com.bueno.account;

import com.bueno.account.entity.Account;
import com.bueno.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;


@SpringBootTest
@Disabled
public class AccountTests {

    @Container
    public static PostgreSQLContainer<PostgresContainersConfiguration> container =
            PostgresContainersConfiguration.getInstance();
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void shouldInsertNewAccountWithDefaultValues() {
        Account account = new Account();
        account.setName("Teste");
        account.setLimite(1000L);
        account.setSaldo(1000L);
        Account saved = accountRepository.save(account);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals("Teste", saved.getName());
        Assertions.assertEquals(1000L, saved.getLimite());
        Assertions.assertEquals(1000L, saved.getSaldo());
    }
}