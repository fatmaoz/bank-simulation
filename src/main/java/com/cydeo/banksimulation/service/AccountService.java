package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    void deleteAccount(Long accountId);

    AccountDTO retrieveById(Long accountId);
}
