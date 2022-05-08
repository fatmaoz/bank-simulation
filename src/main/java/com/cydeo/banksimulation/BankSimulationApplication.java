package com.cydeo.banksimulation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BankSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSimulationApplication.class);
    }


    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }
}
