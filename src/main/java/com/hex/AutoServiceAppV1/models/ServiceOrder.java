package com.hex.AutoServiceAppV1.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Выберите дату и время для записи")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date executionDate;

    @ManyToOne
    private User user;

    @NotEmpty(message = "Выберите услуги")
    @ManyToMany(targetEntity=CarService.class)
    private List<CarService> services;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
