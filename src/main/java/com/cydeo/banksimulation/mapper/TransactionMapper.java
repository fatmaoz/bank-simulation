package com.cydeo.banksimulation.mapper;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;
import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    public Transaction convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, Transaction.class);
    }

    public TransactionDTO convertToDto(Transaction transaction){
        return modelMapper.map(transaction,TransactionDTO.class);
    }
}
