package com.hex.AutoServiceAppV1.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDetailsForm {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String password;

    private String passwordConfirmation;

    @Email(message = "Введите корректный адрес почты")
    @NotBlank(message = "Email не может быть пустым")
    @Size(max = 50, message = "Максимум 50 символов")
    private String email;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Size(min = 7, max = 12, message = "Необходимо 7 - 12 знаков без пробелов и скобок")
    private String phoneNumber;

    public UserDetailsForm() {

    }

    public UserDetailsForm(User user) {
        this.setEmail(user.getEmail());
        this.setPhoneNumber(user.getPhoneNumber());
    }
}
