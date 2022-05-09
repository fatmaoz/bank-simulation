package com.cydeo.banksimulation.service.impl;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.mapper.AccountMapper;
import com.cydeo.banksimulation.repository.AccountRepository;
import com.cydeo.banksimulation.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

//DONE
public AccountDTO createNewAccount(Long id,BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
    AccountDTO account = new AccountDTO(id,accountType,AccountStatus.ACTIVE,creationDate,userId,balance);
    accountRepository.save(accountMapper.convertToEntity(account));
    return account;
}
//DONE
    @Override
    public List<AccountDTO> listAllAccount() {

        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(accountMapper::convertToDto).collect(Collectors.toList());

    }

    public void deleteAccount(Long accountId){
        Account account = accountRepository.getById(accountId);
        account.setAccountStatus(AccountStatus.DELETED);
        accountRepository.save(account);
    }

    @Override
    public AccountDTO retrieveById(Long accountId){

        return accountMapper.convertToDto(accountRepository.getById(accountId));
    }
}
