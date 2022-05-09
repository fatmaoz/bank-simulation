package com.cydeo.banksimulation.service.impl;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;
import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.entity.Transaction;
import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.exception.AccountOwnerShipException;
import com.cydeo.banksimulation.exception.BadRequestException;
import com.cydeo.banksimulation.exception.BalanceNotSufficientException;
import com.cydeo.banksimulation.exception.UnderConstructionException;
import com.cydeo.banksimulation.mapper.AccountMapper;
import com.cydeo.banksimulation.mapper.TransactionMapper;
import com.cydeo.banksimulation.repository.AccountRepository;
import com.cydeo.banksimulation.repository.TransactionRepository;
import com.cydeo.banksimulation.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public TransactionDTO makeTransfer(BigDecimal amount, Date creationDate, AccountDTO sender, AccountDTO receiver, String message) {

        if (!underConstruction) {

            checkAccountOwnerShip(sender, receiver);
            validateAccounts(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            TransactionDTO dto = new TransactionDTO(sender, receiver, amount, message, creationDate);

            Transaction transaction = transactionMapper.convertToEntity(dto);

            transactionRepository.save(transaction);

            return dto;

        } else {
            throw new UnderConstructionException("Make transfer is not possible for now. Please try again later");
        }

    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {

        if (checkSenderBalance(sender, amount)) {

            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));

            Account senderAcc = accountRepository.getById(sender.getId());
            senderAcc.setBalance(sender.getBalance());
            accountRepository.save(senderAcc);

            Account receiverAcc = accountRepository.getById(receiver.getId());
            receiverAcc.setBalance(receiver.getBalance());
            accountRepository.save(receiverAcc);

        } else {
            throw new BalanceNotSufficientException("Balance is not enough for this transaction");
        }

    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        return accountRepository.getById(sender.getId()).getBalance().subtract(amount).compareTo(BigDecimal.ZERO) > 0;
    }

    private void validateAccounts(AccountDTO sender, AccountDTO receiver) {

        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or receiver can not be null");
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender account needs to be different from receiver account");
        }

        if (sender.getAccountStatus().equals(AccountStatus.DELETED)) {
            throw new BadRequestException("Sender account is deleted, you can not send money from this account");
        }

        if (receiver.getAccountStatus().equals(AccountStatus.DELETED)) {
            throw new BadRequestException("Receiver account is deleted, you can not send money to this account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private AccountDTO findAccountById(Long accountId) {
        return accountMapper.convertToDto(accountRepository.getById(accountId));
    }

    private void checkAccountOwnerShip(AccountDTO sender, AccountDTO receiver) {

        if ((sender.getAccountType().equals(AccountType.SAVINGS) || receiver.getAccountType().equals(AccountType.SAVINGS))
                && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AccountOwnerShipException("When one of the account type is SAVINGS, sender and receiver has to be the same person");
        }

    }

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream().map(transactionMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findTransactionListByAccountId(Long accountId) {
        List<Transaction> transactionList = transactionRepository.findAllByAccountId(accountId);
        return transactionList.stream().map(transactionMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> retrieveLastTransactions() {
        return transactionRepository.findLastTenTransaction().stream().map(transactionMapper::convertToDto).collect(Collectors.toList());
    }

}
