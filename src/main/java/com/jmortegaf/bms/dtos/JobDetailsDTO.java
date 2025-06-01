package com.jmortegaf.bms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmortegaf.bms.models.Job;

import java.time.LocalDate;

public record JobDetailsDTO(
        Long id,
        ClientDTO client,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        String description,
        Integer price,
        Boolean paymentState) {
    public JobDetailsDTO(Job job){
        this(job.getId(), new ClientDTO(job.getClient()), job.getDate(), job.getDescription(), job.getPrice(), job.getPaymentState());
    }
}
