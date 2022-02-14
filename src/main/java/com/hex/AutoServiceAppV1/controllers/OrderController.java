package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.OrderForm;
import com.hex.AutoServiceAppV1.services.OrderService;
import com.hex.AutoServiceAppV1.services.ServicesListGeneratorService;
import com.hex.AutoServiceAppV1.services.TimetableGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    ServicesListGeneratorService servicesListGeneratorService;
    @Autowired
    TimetableGeneratorService timetableGeneratorService;

    @GetMapping
    public String showOrderForm(Model model){
        model.addAttribute("serviceList", servicesListGeneratorService.getAllServices());
        model.addAttribute("timeSlotsList", timetableGeneratorService.getAvailableSlots());
        model.addAttribute("order", new OrderForm());

        return "order";
    }

    @PostMapping
    public String processOrder(@ModelAttribute("order") OrderForm order){
        orderService.saveNewOrder(order);
        return "redirect:/";
    }
}
