package com.hex.AutoServiceAppV1.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "service_order")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date placedAt;

    @ManyToOne
    private User user;

    @ManyToMany(targetEntity=CarService.class)
    private List<CarService> services;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
