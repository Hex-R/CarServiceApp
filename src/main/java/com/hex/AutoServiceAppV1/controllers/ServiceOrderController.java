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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/service_order")
public class ServiceOrderController {

    @Autowired
    CarServiceRepository carServiceRepository;

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @GetMapping
    public String showServiceOrderForm(Model model){
        model.addAttribute("order", new ServiceOrder());
        addServicesToModel(model);
        return "service_order";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") ServiceOrder order,
                               BindingResult bindingResult,
                               Model model,
                               @AuthenticationPrincipal User currentUser){

        if (bindingResult.hasErrors()){
            addServicesToModel(model);
            return "service_order";
        }

        order.setUser(currentUser);
        serviceOrderRepository.save(order);

        return "redirect:/user_profile";
    }

    private void addServicesToModel(Model model){
        model.addAttribute("maintenanceServices",
                carServiceRepository.findByType(ServiceType.MAINTENANCE));

        model.addAttribute("diagnosticsServices",
                carServiceRepository.findByType(ServiceType.DIAGNOSTICS));

        model.addAttribute("repairServices",
                carServiceRepository.findByType(ServiceType.REPAIR));

        model.addAttribute("bodyRepairServices",
                carServiceRepository.findByType(ServiceType.BODY_REPAIR));
    }
}