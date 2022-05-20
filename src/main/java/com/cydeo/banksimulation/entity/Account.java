package com.cydeo.banksimulation.entity;

import com.cydeo.banksimulation.enums.AccountStatus;
import com.cydeo.banksimulation.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause= "account_status=DELETED")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column(columnDefinition = "DATE")
    private Date creationDate;
    private Long userId;
    private BigDecimal balance;

//    @ManyToMany
//    @JoinTable(name = "account_campaign",
//            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CAMPAIGN_ID"))
//    private List<Campaign> campaigns;

}
