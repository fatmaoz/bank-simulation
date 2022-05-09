package com.cydeo.banksimulation.converter;


import com.cydeo.banksimulation.dto.AccountDTO;
import com.cydeo.banksimulation.service.AccountService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class AccountDtoConverter implements Converter<String, AccountDTO> {

    AccountService accountService;

    public AccountDtoConverter(@Lazy AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public AccountDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }

        return accountService.retrieveById(Long.parseLong(source));
    }
}
