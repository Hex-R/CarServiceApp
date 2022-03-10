package com.hex.AutoServiceAppV1.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Min(value = 4, message = "Минимум 4 символа")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Min(value = 4, message = "Минимум 4 символа")
    private String password;

    @Email(message = "Введите корректный адрес почты")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Min(value = 10, message = "Минимум 10 цифр")
    private String phoneNumber;

    private String activationCode;

    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getActive();
    }
}
