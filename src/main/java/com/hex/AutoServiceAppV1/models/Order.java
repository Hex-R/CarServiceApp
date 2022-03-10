package com.hex.AutoServiceAppV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Order {

    private long id;
    private ArrayList<CarService> chosenServices;
    private TimeSlot selectedSlot;
}
