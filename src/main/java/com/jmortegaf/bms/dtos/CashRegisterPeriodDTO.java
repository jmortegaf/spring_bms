package com.jmortegaf.bms.dtos;

import com.jmortegaf.bms.models.CashRegister;
import com.jmortegaf.bms.models.CashRegisterEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public record CashRegisterPeriodDTO(
        Long id,
        @NotBlank String period,
        @NotNull Integer initialBalance,
        Integer payments,
        Integer charges,
        Integer finalBalance,
        List<CashRegisterEntryDTO> entries) {
    public CashRegisterPeriodDTO(CashRegister cashRegister) {
        this(
                cashRegister.getId(),
                cashRegister.getPeriod(),
                cashRegister.getInitialBalance(),
                cashRegister.getPayments(),
                cashRegister.getCharges(),
                cashRegister.getFinalBalance(),
                cashRegister.getEntries().stream()
                        .sorted(Comparator.comparing(CashRegisterEntry::getDate))
                        .map(CashRegisterEntryDTO::new).toList());
    }

}
