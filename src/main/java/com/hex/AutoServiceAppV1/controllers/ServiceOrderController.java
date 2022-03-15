package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceOrder;
import com.hex.AutoServiceAppV1.models.ServiceType;
import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.repositories.CarServiceRepository;
import com.hex.AutoServiceAppV1.repositories.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service_order")
public class ServiceOrderController {

    @Autowired
    CarServiceRepository carServiceRepository;

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @GetMapping
    public String showServiceOrderForm(Model model){

        model.addAttribute("Order", new ServiceOrder());

        model.addAttribute("maintenanceServices",
                carServiceRepository.findByType(ServiceType.MAINTENANCE));

        model.addAttribute("diagnosticsServices",
                carServiceRepository.findByType(ServiceType.DIAGNOSTICS));

        model.addAttribute("repairServices",
                carServiceRepository.findByType(ServiceType.REPAIR));

        model.addAttribute("bodyRepairServices",
                carServiceRepository.findByType(ServiceType.BODY_REPAIR));

        return "service_order";
    }

    @PostMapping
    public String processOrder(@AuthenticationPrincipal User currentUser, ServiceOrder newOrder){

        newOrder.setUser(currentUser);
        serviceOrderRepository.save(newOrder);

        return "redirect:/user_profile";
    }
}