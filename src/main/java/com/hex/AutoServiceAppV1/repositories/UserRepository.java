package com.hex.AutoServiceAppV1.repositories;

import com.hex.AutoServiceAppV1.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}
