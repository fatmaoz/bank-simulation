package com.cydeo.banksimulation.repository;

import com.cydeo.banksimulation.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public List<TransactionDTO> transactionDTOList = new ArrayList<>();

    public TransactionDTO save(TransactionDTO transactionDTO){
        transactionDTOList.add(transactionDTO);
        return transactionDTO;
    }

    public List<TransactionDTO> findAll() {
        return transactionDTOList;
    }

    public List<TransactionDTO> findByAccountId(UUID account) {
        return transactionDTOList.stream().filter(
                transactionDTO -> transactionDTO.getSender().equals(account)
                    || transactionDTO.getReceiver().equals(account)).collect(Collectors.toList());
    }

    public List<TransactionDTO> retrieveLastTransactions() {
        return transactionDTOList.stream().
                sorted(Comparator.comparing(TransactionDTO::getCreationDate)).limit(10).collect(Collectors.toList());
    }
}
