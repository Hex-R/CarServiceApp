package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserService userService = new UserService();

    @GetMapping
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())
            return "registration";

        if (userService.addUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("usernameError", "User already exists");
            return "registration";
        }
    }

    @GetMapping("/activation/{code}")
    public String activateAccount(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code not found");
        }

        return "/login";
    }
}
