package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceForCar;
import com.hex.AutoServiceAppV1.services.ServicesListGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceForCarConverter implements Converter<String, ServiceForCar> {

    @Autowired
    ServicesListGeneratorService servicesListGeneratorService;

    @Override
    public ServiceForCar convert(String id) {
        int parsedID = Integer.parseInt(id);
        return servicesListGeneratorService.getAllServices().get(parsedID - 1);
    }
}
