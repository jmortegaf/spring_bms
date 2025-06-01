package com.jmortegaf.bms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmortegaf.bms.models.CashRegisterEntry;
import com.jmortegaf.bms.models.EntryType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CashRegisterEntryDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull LocalDate date,
        String description,
        EntryType entryType,
        Integer amount) {

    public CashRegisterEntryDTO(CashRegisterEntry entry){
        this(entry.getId(), entry.getDate(), entry.getDescription(), entry.getEntryType(), entry.getAmount());
    }
}
