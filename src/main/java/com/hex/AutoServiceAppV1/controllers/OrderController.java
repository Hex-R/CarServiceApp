package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.OrderForm;
import com.hex.AutoServiceAppV1.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    @GetMapping
    public String orderForm(){

    }

    @PostMapping
    public String processOrder(OrderForm orderForm){
        orderService.saveNewOrder(orderForm);
        return "redirect:/";
    }
}
