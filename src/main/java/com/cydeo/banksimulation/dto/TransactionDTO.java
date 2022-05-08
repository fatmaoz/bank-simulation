package com.cydeo.banksimulation.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
public class TransactionDTO {


    Long id;

    @NotNull
    private UUID sender;
    @NotNull
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
