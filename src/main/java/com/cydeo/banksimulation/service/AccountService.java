package com.cydeo.banksimulation.service;

import com.cydeo.banksimulation.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    List<AccountDTO> listAllActiveAccount();

    void deleteAccount(Long accountId);

    AccountDTO retrieveById(Long accountId);

}
