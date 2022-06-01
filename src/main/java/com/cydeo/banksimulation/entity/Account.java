package com.cydeo.banksimulation.entity;


import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column(columnDefinition = "DATE")
    private Date creationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private BigDecimal balance;
//    @ManyToMany
//    @JoinTable(name = "account_campaign",
//            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CAMPAIGN_ID"))
//    private List<Campaign> campaigns;
}
