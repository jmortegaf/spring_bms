package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.LoginDTO;
import com.jmortegaf.bms.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

}
