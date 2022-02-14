package com.hex.AutoServiceAppV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {

    private int id;
    private String day;
    private String time;
}
