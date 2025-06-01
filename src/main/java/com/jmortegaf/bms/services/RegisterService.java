package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.RegisterDTO;
import com.jmortegaf.bms.models.User;
import com.jmortegaf.bms.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> register(@Valid RegisterDTO registerDTO) {
        User user = new User(registerDTO.user(), passwordEncoder.encode(registerDTO.password()));
        userRepository.save(user);
        return ResponseEntity.ok("User created");
    }
}
