package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.ServiceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceDtoConverter implements Converter<String, ServiceDTO> {

    @Override
    public ServiceDTO convert(String id) {
        int parsedID = Integer.parseInt(id);
        return OrderController.allServices.get(parsedID - 1);
    }
}
