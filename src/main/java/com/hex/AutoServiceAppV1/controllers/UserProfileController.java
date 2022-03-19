package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.repositories.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user_profile")
public class UserProfileController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @GetMapping
    public String showProfilePage(Model model){

        model.addAttribute("activeOrders", serviceOrderRepository.findByIsCompleted(false));
        model.addAttribute("completedOrders", serviceOrderRepository.findByIsCompleted(true));

        return "user_profile";
    }
}
