package com.hex.AutoServiceAppV1.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 4, max = 30, message = "Необходимо 4 - 30 символов")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 70, message = "Необходимо 6 - 30 символов")
    private String password;

    @Email(message = "Введите корректный адрес почты")
    @NotBlank(message = "Email не может быть пустым")
    @Size(max = 50, message = "Максимум 50 символов")
    private String email;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Size(min = 7, max = 12, message = "Необходимо 7 - 12 знаков без пробелов и скобок")
    private String phoneNumber;

    @OneToMany
    private List<ServiceOrder> serviceOrders;

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
