package com.cydeo.banksimulation.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    @NotNull
    private UUID sender;
    @NotNull
    private UUID receiver;

    @NotNull
    private BigDecimal amount;
    @NotEmpty
    @Size(min = 2, max = 250)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String message;
    private Date creationDate;

}
