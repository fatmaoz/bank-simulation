package com.cydeo.banksimulation.converter;


import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.dto.TransactionDTO;
import com.cydeo.banksimulation.service.AccountService;
import com.cydeo.banksimulation.service.TransactionService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class TransactionDtoConverter implements Converter<String, TransactionDTO> {

   TransactionService transactionService;

    public TransactionDtoConverter(@Lazy TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public TransactionDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

        return transactionService.findById(Long.parseLong(source));
    }
}
