package com.cydeo.banksimulation;

import com.cydeo.banksimulation.entity.Account;
import com.cydeo.banksimulation.enums.AccountType;
import com.cydeo.banksimulation.service.AccountService;
import com.cydeo.banksimulation.service.TransactionService;
import com.cydeo.banksimulation.service.impl.AccountServiceImpl;
import com.cydeo.banksimulation.service.impl.TransactionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSimulationApplication.class);
    }

}
