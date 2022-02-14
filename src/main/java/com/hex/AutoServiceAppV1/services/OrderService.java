package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.Order;
import com.hex.AutoServiceAppV1.models.OrderForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    ArrayList<Order> currentOrders = new ArrayList<>();

    public void saveNewOrder(OrderForm orderForm){
        currentOrders.add(new Order(generateID(),
                orderForm.getChosenServices(), orderForm.getSelectedSlot()));

        System.out.println(orderForm);
        System.out.println(currentOrders);
    }

    private long generateID(){
        return (long)(Math.random()*6000);
    }
}
