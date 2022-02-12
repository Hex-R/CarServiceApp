package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.services.ClientAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClientAccountController {

    ClientAccountService clientAccountService;

    @GetMapping
    public String home(){
        return "home";
    }
}
