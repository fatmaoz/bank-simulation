package com.cydeo.banksimulation.dto;

import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    @NotNull
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date creationDate;

    @NotNull()
    private Long userId;

    @NotNull()
    @Positive
    private BigDecimal balance;

}
