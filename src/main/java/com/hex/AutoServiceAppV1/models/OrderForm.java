package com.hex.AutoServiceAppV1.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderForm {

    private ArrayList<ServiceDTO> chosenServices;
    private TimeSlot selectedSlot;
}
