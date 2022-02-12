package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.ClientAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ClientAccountService {

    ArrayList<ClientAccount> accounts = new ArrayList<>();

    private void saveNewAccount(String userName, String userPassword, String userPhoneNumber){
        ClientAccount newAccount = new ClientAccount(id(), userName, userPassword, userPhoneNumber, createdAt());
        System.out.println(newAccount);
        accounts.add(newAccount);
    }

    private long id(){
        return (long) (Math.random()*6000);
    }

    private Date createdAt(){
        return new Date();
    }
}
