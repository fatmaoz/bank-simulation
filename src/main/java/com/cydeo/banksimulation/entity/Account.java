package com.cydeo.banksimulation.entity;


import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date creationDate;
    @NotNull(message = "UserId can not be null")
    private Long userId;
    @NotNull(message = "Balance can not be null")
    private BigDecimal balance;

}
