package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.ServiceDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceListGeneratorService {

    ArrayList<ServiceDTO> allServices;

    private void servicesListGenerator(){
        List<ServiceDTO> services = Arrays.asList(
                new ServiceDTO(1, "ТО"),
                new ServiceDTO(2, "Замена масла в двигателе"),
                new ServiceDTO(3, "Замена масла в коробке"),
                new ServiceDTO(4, "Замена тормозных колодок"),
                new ServiceDTO(5, "Диагностика"),
                new ServiceDTO(6, "Шиномонтаж"),
                new ServiceDTO(7, "Полировка")
        );
        allServices.addAll(services);
    }
}
