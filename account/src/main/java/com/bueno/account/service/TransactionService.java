package com.bueno.account.service;

import com.bueno.account.dto.ExtratoResponseDto;
import com.bueno.account.dto.TransactionDto;
import com.bueno.account.dto.TransactionRequestDto;
import com.bueno.account.entity.Account;
import com.bueno.account.entity.Transaction;
import com.bueno.account.entity.enums.TransactionType;
import com.bueno.account.exception.NotFoundException;
import com.bueno.account.exception.ResourceUnprocessableException;
import com.bueno.account.mapper.TransactionMapper;
import com.bueno.account.repository.AccountRepository;
import com.bueno.account.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Transactional
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(readOnly = true)
    public TransactionDto getTransactionByIdAndAccount(Long transactionId, Long accountId) {
        return transactionMapper.toDto(transactionRepository.findTransactionByIdAndAccount_Id(transactionId, accountId)
                                                            .orElseThrow(() -> new NotFoundException(
                                                                    "Transaction not found with id: " + transactionId))
        );
    }

    @Transactional(readOnly = true)
    public ExtratoResponseDto fetchExtratoByAccountId(Long accountId) {
        Account account = getAccount(accountId);

        return ExtratoResponseDto.builder()
                                 .balance(account.getSaldo())
                                 .balanceDate(LocalDateTime.now())
                                 .limit(account.getLimite())
                                 .lastTransactions(
                                         transactionRepository.findLastTenTransactionsOrderByDateDesc(accountId)
                                                              .stream()
                                                              .map(transactionMapper::toExtratoItemDto)
                                                              .toList())
                                 .build();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public TransactionDto addNewTransaction(Long accountId, TransactionRequestDto dto) {

        Account account = getAccount(accountId);

        validateTransaction(account, dto);

        return performTransaction(account, dto);
    }

    private TransactionDto performTransaction(Account account, TransactionRequestDto dto) {

        if (TransactionType.D.equals(dto.type())) {
            account.setSaldo(account.getSaldo() - dto.value());
        } else {
            account.setSaldo(account.getSaldo() + dto.value());
        }

        Transaction newTransaction = Transaction.builder()
                                                .account(account)
                                                .description(dto.description())
                                                .type(dto.type())
                                                .value(dto.value())
                                                .build();

        accountRepository.save(account);
        Transaction saved = transactionRepository.save(newTransaction);

        return transactionMapper.toDto(saved);
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                                .orElseThrow(() -> new NotFoundException("Account not found with id: " + accountId));
    }

    private void validateTransaction(Account account, TransactionRequestDto dto) {
        if (TransactionType.D.equals(dto.type()) && account.getSaldo() + account.getLimite() < dto.value()) {
            throw new ResourceUnprocessableException(
                    "Account does not have enough balance to perform this transaction");
        }
    }


}
