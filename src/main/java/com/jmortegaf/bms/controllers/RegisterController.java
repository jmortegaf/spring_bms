package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.RegisterDTO;
import com.jmortegaf.bms.services.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {


    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO){
        return registerService.register(registerDTO);
    }
}
