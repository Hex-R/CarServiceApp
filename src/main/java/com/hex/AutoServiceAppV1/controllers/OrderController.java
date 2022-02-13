package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.OrderForm;
import com.hex.AutoServiceAppV1.models.ServiceDTO;
import com.hex.AutoServiceAppV1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    static ArrayList<ServiceDTO> allServices = new ArrayList<>();

    @Autowired
    OrderService orderService;

    @GetMapping
    public String showOrderForm(Model model){
        List<ServiceDTO> services = Arrays.asList(
                new ServiceDTO(1, "ТО"),
                new ServiceDTO(2, "Замена масла в двигателе"),
                new ServiceDTO(3, "Замена масла в коробке"),
                new ServiceDTO(4, "Замена тормозных колодок"),
                new ServiceDTO(5, "Диагностика"),
                new ServiceDTO(6, "Шиномонтаж"),
                new ServiceDTO(7, "Полировка")
        );
        allServices.addAll(services);
        model.addAttribute("serviceList", allServices);
        model.addAttribute("order", new OrderForm());

        return "order";
    }

    @PostMapping
    public String processOrder(@ModelAttribute("order") OrderForm order){
        orderService.saveNewOrder(order);
        return "redirect:/";
    }
}
