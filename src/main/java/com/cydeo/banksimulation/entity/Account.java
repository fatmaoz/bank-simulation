package com.cydeo.banksimulation.entity;

import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date creationDate;
    private Long userId;
    private BigDecimal balance;

//    @ManyToMany
//    @JoinTable(name = "account_campaign",
//            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CAMPAIGN_ID"))
//    private List<Campaign> campaigns;

}
