package com.cydeo.banksimulation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login", "/"})  //localhost:8080/ or localhost:8080/login
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String welcome() {
        return "index";
    }

}