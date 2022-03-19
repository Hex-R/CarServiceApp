package com.hex.AutoServiceAppV1.repositories;

import com.hex.AutoServiceAppV1.models.ServiceOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceOrderRepository extends CrudRepository<ServiceOrder, Long> {

    List<ServiceOrder> findByIsCompleted(boolean isCompleted);
}
