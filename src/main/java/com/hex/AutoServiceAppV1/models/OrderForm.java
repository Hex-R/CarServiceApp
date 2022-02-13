package com.hex.AutoServiceAppV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {

    private ArrayList<ServiceDTO> chosenServices;
    private TimeSlot selectedSlot;
}
