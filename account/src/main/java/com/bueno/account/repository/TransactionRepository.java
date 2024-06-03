package com.bueno.account.repository;


import com.bueno.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findTransactionByIdAndAccount_Id(Long id, Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.id = :id AND t.account.id = :accountId ")
    Optional<Transaction> findMyTransactionByIdAndAccount(@Param("id") Long id, @Param("accountId") Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId ORDER BY t.createdAt DESC LIMIT 10 ")
    List<Transaction> findLastTenTransactionsOrderByDateDesc(@Param("accountId") Long accountId);

}
