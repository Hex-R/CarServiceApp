package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceType;
import com.hex.AutoServiceAppV1.repositories.CarServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final CarServiceRepository carServiceRepository;

    public MainController(CarServiceRepository carServiceRepository) {
        this.carServiceRepository = carServiceRepository;
    }

    @GetMapping("/maintenance")
    public String showMaintenanceServices(Model model) {

        model.addAttribute("maintenanceServices",
                carServiceRepository.findByType(ServiceType.MAINTENANCE));

        return "maintenance";
    }

    @GetMapping("/diagnostics")
    public String showDiagnosticsServices(Model model) {

        model.addAttribute("diagnosticsServices",
                carServiceRepository.findByType(ServiceType.DIAGNOSTICS));

        return "diagnostics";
    }

    @GetMapping("/repair")
    public String showRepairServices(Model model) {

        model.addAttribute("repairServices",
                carServiceRepository.findByType(ServiceType.REPAIR));

        return "repair";
    }

    @GetMapping("/body_repair")
    public String showBodyRepairServices(Model model) {

        model.addAttribute("bodyRepairServices",
                carServiceRepository.findByType(ServiceType.BODY_REPAIR));

        return "body_repair";
    }

    @GetMapping("/tire_fitting")
    public String showTireFittingServices(Model model) {

        model.addAttribute("tireFittingServices",
                carServiceRepository.findByType(ServiceType.TIRE_FITTING));

        return "tire_fitting";
    }

    @GetMapping("/contacts")
    public String showContactsPage() {
        return "contacts";
    }
}
