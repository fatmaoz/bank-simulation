package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AccountController {


    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/index")
    public String accountList (Model model){
        model.addAttribute("accountList", accountService.listAllAccount());
        return "/account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model){
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accountTypes", AccountType.values());
        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") AccountDTO account, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("accountTypes", AccountType.values());
            return "account/create-account";
        }
        else {
            accountService.createNewAccount(account.getId(),account.getBalance(),
                    new Date(),
                    account.getAccountType(),
                    account.getUserId());

            model.addAttribute("accountList",accountService.listAllAccount());

            return "redirect:/index";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return "redirect:/index";
    }
    
//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam UUID id, Model model) {
//        accountService.deleteAccount(id);
//        return "redirect:/index";
//    }
    
    


}
