package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.service.AccountService;
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
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/create-form")
    public String getCreateForm(Model model){
        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());
        return "create-account";
    }

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("accountTypes", AccountType.values());
            return "create-account";
        }
        else {
            accountService.createNewAccount(account.getBalance(),
                    new Date(),
                    account.getAccountType(),
                    account.getUserId());

            model.addAttribute(accountService.listAllAccount());

            return "redirect:/index";
        }
    }

    @GetMapping("/index")
    public String accountList (Model model){
        model.addAttribute("accountList", accountService.listAllAccount());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        accountService.deleteAccount(id);
        return "redirect:/index";
    }
    
//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam UUID id, Model model) {
//        accountService.deleteAccount(id);
//        return "redirect:/index";
//    }
    
    


}
