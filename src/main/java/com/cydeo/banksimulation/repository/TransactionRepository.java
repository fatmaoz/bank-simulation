package com.cydeo.banksimulation.repository;

import com.cydeo.banksimulation.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll() {
        return  transactionList;
    }

    public List<Transaction> findByAccountId(UUID account) {
        return transactionList.stream().filter(
            transaction-> transaction.getSender().equals(account)
                    || transaction.getReceiver().equals(account)).collect(Collectors.toList());
    }

    public List<Transaction> retrieveLastTransactions() {
        return transactionList.stream().
                sorted(Comparator.comparing(Transaction::getCreationDate)).limit(10).collect(Collectors.toList());
    }
}
