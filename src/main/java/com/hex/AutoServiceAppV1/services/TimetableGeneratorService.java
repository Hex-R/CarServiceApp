package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.TimeSlot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TimetableGeneratorService {

    ArrayList<TimeSlot> availableSlots;

    private void timetableBuilder(){
        List<TimeSlot> slots = Arrays.asList(
                new TimeSlot("Понедельник","09:00"),
                new TimeSlot("Понедельник","13:00"),
                new TimeSlot("Понедельник","17:00"),
                new TimeSlot("Вторник","09:00"),
                new TimeSlot("Вторник","13:00"),
                new TimeSlot("Вторник","17:00"),
                new TimeSlot("Среда","09:00"),
                new TimeSlot("Среда","13:00"),
                new TimeSlot("Среда","17:00"),
                new TimeSlot("Четверг","09:00"),
                new TimeSlot("Четверг","13:00"),
                new TimeSlot("Четверг","17:00"),
                new TimeSlot("Пятница","09:00"),
                new TimeSlot("Пятница","13:00"),
                new TimeSlot("Пятница","17:00")
        );
        availableSlots.addAll(slots);
    }
}
