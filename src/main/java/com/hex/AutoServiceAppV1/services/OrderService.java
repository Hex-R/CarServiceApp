package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.Order;
import com.hex.AutoServiceAppV1.models.OrderForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    ArrayList<Order> currentOrders;

    public void saveNewOrder(OrderForm orderForm){
        currentOrders.add(new Order(generateID(),
                orderForm.getChosenServices(), orderForm.getSelectedSlot()));
    }

    private long generateID(){
        return (long)(Math.random()*6000);
    }
}
