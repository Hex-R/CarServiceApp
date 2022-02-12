package com.hex.AutoServiceAppV1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ClientAccount {

    private long id;
    private String name;
    private String password;
    private String phoneNumber;
    private Date createdAt;
}
