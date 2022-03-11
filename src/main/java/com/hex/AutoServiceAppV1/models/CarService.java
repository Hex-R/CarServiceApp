package com.hex.AutoServiceAppV1.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_service")
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private ServiceType type;

    private String name;

    private String description;

    private int price;
}
