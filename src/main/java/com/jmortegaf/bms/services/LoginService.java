package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.LoginDTO;
import com.jmortegaf.bms.models.User;
import com.jmortegaf.bms.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseEntity<?> login(@Valid LoginDTO loginDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.user(),loginDTO.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken=tokenService.generateToken((User)authenticatedUser.getPrincipal());
        return ResponseEntity.ok(Map.of("token",JWTToken));
    }
}
