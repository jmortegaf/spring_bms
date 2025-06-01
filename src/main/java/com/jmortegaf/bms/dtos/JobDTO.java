package com.jmortegaf.bms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmortegaf.bms.models.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record JobDTO(
        Long id,
        @NotNull Long clientId,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull LocalDate date,
        @NotBlank String description,
        @NotNull Integer price,
        @NotNull Boolean paymentState) {
    public JobDTO(Job job){
        this(job.getId(), job.getClient().getId(), job.getDate(), job.getDescription(), job.getPrice(), job.getPaymentState());
    }
}
