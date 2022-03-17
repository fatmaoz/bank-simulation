package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.entity.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction makeTransfer(BigDecimal amount, Date creationDate,
                             Account sender, Account receiver,
                             String message);

    List<Transaction> findAll();

    List<Transaction> findTransactionListByAccountId(UUID account);

    List<Transaction> retrieveLastTransactions();
}
