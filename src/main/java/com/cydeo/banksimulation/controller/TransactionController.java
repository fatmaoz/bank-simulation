package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;
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
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;


    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String retrieveTransactionDetailByAccountId(Model model) {
        model.addAttribute("transaction", new TransactionDTO());
        model.addAttribute("accounts", accountService.listAllAccount());
        model.addAttribute("lastTransactionList", transactionService.retrieveLastTransactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("accounts", accountService.listAllAccount());
            return "transaction/make-transfer";
        }
        AccountDTO receiver = accountService.retrieveById(transactionDTO.getReceiver().getUserId());
        AccountDTO sender = accountService.retrieveById(transactionDTO.getSender().getUserId());
        transactionService.makeTransfer(transactionDTO.getAmount(), new Date(), sender, receiver, transactionDTO.getMessage());
        return "redirect:/make-transfer";
    }

    @GetMapping("/transactions/{id}")
    public String retrieveTransactionDetailByAccountId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("transactionList", transactionService.findTransactionListByAccountId(id));
        return "transaction/transactions";
    }



}
