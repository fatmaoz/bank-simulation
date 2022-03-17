package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/create-form")
    public String getCreateForm(Account account, Model model){
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("accountType", AccountType.SAVINGS);
        return "create-account";
    }


    @PostMapping("/create")
    public String createAccount(Account account, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "create-account";
        }
        accountService.createNewAccount(account.getBalance(),
                new Date(),
                account.getAccountType(),
                account.getUserId());
        model.addAttribute(accountService.listAllAccount());
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String accountList (Model model){
        model.addAttribute("accountList", accountService.listAllAccount());
        return "index";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id, Model model) {
        accountService.deleteAccount(id);
        return "redirect:/index";
    }
}
