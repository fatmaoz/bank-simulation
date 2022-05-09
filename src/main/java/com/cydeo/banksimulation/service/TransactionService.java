package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    TransactionDTO makeTransfer(BigDecimal amount, Date creationDate,
                                AccountDTO sender, AccountDTO receiver,
                                String message);

    List<TransactionDTO> findAll();

    List<TransactionDTO> findTransactionListByAccountId(Long account);

    List<TransactionDTO> retrieveLastTransactions();

}
