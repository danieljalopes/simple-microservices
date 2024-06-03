package com.bueno.account.service;

import com.bueno.account.dto.AccountDto;
import com.bueno.account.exception.NotFoundException;
import com.bueno.account.mapper.AccountMapper;
import com.bueno.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long id) {
        return accountMapper.toDto(
                accountRepository.findById(id)
                                 .orElseThrow(() -> new NotFoundException("Account not found with id: " + id))
        );
    }

}
