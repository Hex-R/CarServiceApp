package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.ClientAccount;
import com.hex.AutoServiceAppV1.models.RegistrationForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ClientAccountService {

    ArrayList<ClientAccount> accounts = new ArrayList<>();

    public void saveNewAccount(RegistrationForm registrationForm){
        ClientAccount newAccount = new ClientAccount(id(), registrationForm.getName(),
                registrationForm.getPassword(), registrationForm.getPhoneNumber(), createdAt());
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
