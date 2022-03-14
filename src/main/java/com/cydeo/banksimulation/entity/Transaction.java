package com.cydeo.banksimulation.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    private UUID sender;
    private UUID receiver;
    private BigDecimal amount;
    private String message;
    private Date creationDate;

}
