package com.cydeo.banksimulation.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private String label;

//    @ManyToMany(mappedBy = "campaigns")
//    private List<Account> accountList;

}
