package com.bueno.account.controller;

import com.bueno.account.dto.TransactionDto;
import com.bueno.account.dto.TransactionRequestDto;
import com.bueno.account.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;
    private final String localhost;

    public TransactionController(TransactionService transactionService) throws UnknownHostException {
        this.transactionService = transactionService;
        localhost = InetAddress.getLocalHost().getHostName();
    }

    @GetMapping(value = "/{id}/transactions/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchTransactionByAccountAndId(@PathVariable("id") Long accountId,
                                                            @PathVariable("transactionId") Long transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionByIdAndAccount(transactionId, accountId));
    }

    @GetMapping(value = "/{id}/extrato", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchExtratoLastTenTransactions(@PathVariable("id") Long accountId) {
        return ResponseEntity.ok(transactionService.fetchExtratoByAccountId(accountId));
    }

    @PostMapping(value = "/{id}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewTransaction(@NotNull @PathVariable("id") Long accountId,
                                               @Valid @RequestBody TransactionRequestDto dto) {
        TransactionDto transactionDto = transactionService.addNewTransactionInPessimisticWrite(accountId, dto);
//        TransactionDto transactionDto = transactionService.addNewTransaction(accountId, dto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("hostname", localhost);
        log.info("Host {} - Transaction created: {} ", localhost, transactionDto);
        return new ResponseEntity<>(transactionDto, headers, HttpStatus.CREATED);
    }

}