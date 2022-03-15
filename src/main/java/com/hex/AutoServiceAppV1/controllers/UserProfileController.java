package com.hex.AutoServiceAppV1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user_profile")
public class UserProfileController {

    @GetMapping
    public String showProfilePage(Model model){

        return "user_profile";
    }
}
