package com.jmortegaf.bms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmortegaf.bms.models.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewQuoteDTO(
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull LocalDate date,
        @NotBlank String description,
        @NotNull Long clientId) {}
