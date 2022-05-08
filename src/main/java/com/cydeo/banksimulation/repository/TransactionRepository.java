package com.cydeo.banksimulation.repository;

import com.cydeo.banksimulation.dto.TransactionDTO;
import com.cydeo.banksimulation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    List<Transaction> findAllById(Long id);
//First10TransactionOrderByAsc
    @Query(name ="SELECT * FROM transactions ORDER BY creation_date ASC LIMIT 10",nativeQuery = true)
    List<Transaction> findLastTenTransction();
    // public List<TransactionDTO> transactionDTOList = new ArrayList<>();

//    public TransactionDTO save(TransactionDTO transactionDTO){
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }

//    public List<TransactionDTO> findAll() {
//        return transactionDTOList;
//    }
//
//    public default List<Transaction> findByAccountId(UUID account) {
//        return transactionDTOList.stream().filter(
//                transactionDTO -> transactionDTO.getSender().equals(account)
//                    || transactionDTO.getReceiver().equals(account)).collect(Collectors.toList());
//    }

 //   public List<Transaction> findFirst10ByTransaction();
//    public List<TransactionDTO> retrieveLastTransactions() {
//        return transactionDTOList.stream().
//                sorted(Comparator.comparing(TransactionDTO::getCreationDate)).limit(10).collect(Collectors.toList());
//    }
}
