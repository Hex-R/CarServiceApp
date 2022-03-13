package com.hex.AutoServiceAppV1.repositories;

import com.hex.AutoServiceAppV1.models.CarService;
import com.hex.AutoServiceAppV1.models.ServiceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarServiceRepository extends CrudRepository<CarService, Long> {

    List<CarService> findByType(ServiceType serviceType);
}
