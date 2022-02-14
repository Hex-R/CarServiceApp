package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.TimeSlot;
import com.hex.AutoServiceAppV1.services.TimetableGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotConverter implements Converter<String, TimeSlot> {

    @Autowired
    TimetableGeneratorService timetableGeneratorService;

    @Override
    public TimeSlot convert(String id) {
        int parsedID = Integer.parseInt(id);
        TimeSlot timeSlot = new TimeSlot();
        for (TimeSlot slot : timetableGeneratorService.getAvailableSlots()){
            if (slot.getId() == parsedID){
                timeSlot = slot;
            }
        }
        return timeSlot;
    }
}
