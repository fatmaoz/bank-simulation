package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.entity.Transaction;
import com.cydeo.banksimulation.service.AccountService;
import com.cydeo.banksimulation.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;


    @GetMapping("/make-transfer")
    public String retrieveTransactionDetailByAccountId(Model model) {
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("accounts", accountService.listAllAccount());
        model.addAttribute("lastTransactionList", transactionService.retrieveLastTransactions());

        return "make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "make-transfer";
        }
        Account receiver = accountService.retrieveById(transaction.getReceiver());
        Account sender = accountService.retrieveById(transaction.getSender());
        transactionService.makeTransfer(transaction.getAmount(), new Date(), sender, receiver, transaction.getMessage());
        return "redirect:/make-transfer";
    }

    @GetMapping("/transactions/{id}")
    public String retrieveTransactionDetailByAccountId(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("transactionList", transactionService.findTransactionListByAccountId(id));
        return "transactions";
    }



}
