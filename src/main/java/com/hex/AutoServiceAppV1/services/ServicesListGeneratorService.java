package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.ServiceForCar;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class ServicesListGeneratorService {

    ArrayList<ServiceForCar> allServices = new ArrayList<>();

    @PostConstruct
    public void generateServicesList() {
        List<ServiceForCar> services = Arrays.asList(
                new ServiceForCar(1, "ТО"),
                new ServiceForCar(2, "Замена масла в двигателе"),
                new ServiceForCar(3, "Замена масла в коробке"),
                new ServiceForCar(4, "Замена тормозных колодок"),
                new ServiceForCar(5, "Диагностика"),
                new ServiceForCar(6, "Шиномонтаж"),
                new ServiceForCar(7, "Полировка")
        );
        allServices.addAll(services);
    }
}
