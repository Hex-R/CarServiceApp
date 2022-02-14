package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.TimeSlot;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class TimetableGeneratorService {

    ArrayList<TimeSlot> availableSlots = new ArrayList<>();

    @PostConstruct
    public void generateTimeTable(){
        List<TimeSlot> slots = Arrays.asList(
                new TimeSlot(generateID(),"Понедельник","09:00"),
                new TimeSlot(generateID(),"Понедельник","13:00"),
                new TimeSlot(generateID(),"Понедельник","17:00"),
                new TimeSlot(generateID(),"Вторник","09:00"),
                new TimeSlot(generateID(),"Вторник","13:00"),
                new TimeSlot(generateID(),"Вторник","17:00"),
                new TimeSlot(generateID(),"Среда","09:00"),
                new TimeSlot(generateID(),"Среда","13:00"),
                new TimeSlot(generateID(),"Среда","17:00"),
                new TimeSlot(generateID(),"Четверг","09:00"),
                new TimeSlot(generateID(),"Четверг","13:00"),
                new TimeSlot(generateID(),"Четверг","17:00"),
                new TimeSlot(generateID(),"Пятница","09:00"),
                new TimeSlot(generateID(),"Пятница","13:00"),
                new TimeSlot(generateID(),"Пятница","17:00")
        );
        availableSlots.addAll(slots);
    }

    private int generateID(){
        return (int)(Math.random()*6000);
    }
}
