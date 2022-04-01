package com.cydeo.banksimulation.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
public class Transaction {


    private UUID sender;
    private UUID receiver;

    @NotNull
    @Positive(message = "You can not use minus numbers")
    private BigDecimal amount;
    @NotEmpty
    @Size(min = 2, max = 250)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String message;
    private Date creationDate;

}
