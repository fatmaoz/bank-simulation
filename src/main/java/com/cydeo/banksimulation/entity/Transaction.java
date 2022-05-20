package com.cydeo.banksimulation.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;
    private BigDecimal amount;
    private String message;
    @Column(columnDefinition = "DATE")
    private Date creationDate;

}
