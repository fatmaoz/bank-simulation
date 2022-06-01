package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;
import com.cydeo.banksimulation.service.AccountService;
import com.cydeo.banksimulation.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String retrieveTransactionDetailByAccountId(Model model) {

        model.addAttribute("transactionDTO", new TransactionDTO());
        model.addAttribute("accounts", accountService.listAllActiveAccount());
        model.addAttribute("lastTransactionList", transactionService.retrieveLastTransactions());

        return "transaction/make-transfer";

    }

    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transactionDTO") TransactionDTO transactionDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", accountService.listAllActiveAccount());
            return "transaction/make-transfer";
        }

        AccountDTO receiver = transactionDTO.getReceiver();
        AccountDTO sender = transactionDTO.getSender();
        transactionService.makeTransfer(transactionDTO.getAmount(), new Date(), sender, receiver, transactionDTO.getMessage());

        return "redirect:/transaction/make-transfer";

    }

    @GetMapping("/transactions/{id}")
    public String retrieveTransactionDetailByAccountId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("transactionList", transactionService.findTransactionListByAccountId(id));
        return "transaction/transactions";
    }

}
