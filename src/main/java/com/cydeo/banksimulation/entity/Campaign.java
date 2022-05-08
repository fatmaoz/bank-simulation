package com.cydeo.banksimulation.entity;


import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private String label;
//
//    @ManyToMany(mappedBy = "campaigns")
//    private List<Account> accountList;

}
