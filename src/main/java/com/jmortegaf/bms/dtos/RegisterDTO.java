package com.jmortegaf.bms.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank String user,
        @NotBlank String password) {}
