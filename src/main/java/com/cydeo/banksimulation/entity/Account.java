package com.cydeo.banksimulation.entity;


import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;

    @NotNull
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date creationDate;

    @NotNull()
    private UUID userId;

    @NotNull()
    @Positive
    private BigDecimal balance;

}
