package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.RegistrationForm;
import com.hex.AutoServiceAppV1.services.ClientAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    ClientAccountService clientAccountService = new ClientAccountService();

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        clientAccountService.saveNewAccount(form);
        return "redirect:/main";
    }
}
