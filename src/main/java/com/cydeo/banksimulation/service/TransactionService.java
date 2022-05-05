package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionDTO makeTransfer(BigDecimal amount, Date creationDate,
                                AccountDTO sender, AccountDTO receiver,
                                String message);

    List<TransactionDTO> findAll();

    List<TransactionDTO> findTransactionListByAccountId(UUID account);

    List<TransactionDTO> retrieveLastTransactions();
}
