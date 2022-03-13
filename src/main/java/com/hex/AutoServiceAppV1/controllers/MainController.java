package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceType;
import com.hex.AutoServiceAppV1.repositories.CarServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    CarServiceRepository carServiceRepository;

    @GetMapping("/maintenance")
    public String showMaintenanceServices(Model model){

        model.addAttribute("maintenanceServices",
                carServiceRepository.findByType(ServiceType.MAINTENANCE));

        return "maintenance";
    }

    @GetMapping("/diagnostics")
    public String showDiagnosticsServices(){return " ";}

    @GetMapping("/repair")
    public String showRepairServices(){return " ";}

    @GetMapping("/body_repair")
    public String showBodyRepairServices(){return " ";}

    @GetMapping("/tire_fitting")
    public String showTireFittingServices(){return " ";}

    @GetMapping("/tires_and_wheels")
    public String showTiresAndWheels(){return " ";}

}
