package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    Boolean existsByUsername(@NotBlank String username);
}
