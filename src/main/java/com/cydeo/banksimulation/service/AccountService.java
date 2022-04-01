package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date crationDate, AccountType accountType, UUID userId);

    List<Account> listAllAccount();

    void deleteAccount(UUID accountId);

    Account retrieveById(UUID accountId);
}
