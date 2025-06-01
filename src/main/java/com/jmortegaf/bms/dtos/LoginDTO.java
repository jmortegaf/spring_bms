package com.jmortegaf.bms.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String user,
        @NotBlank String password) {}
