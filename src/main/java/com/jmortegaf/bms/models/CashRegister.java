package com.jmortegaf.bms.models;

import com.jmortegaf.bms.dtos.CashRegisterEntryDTO;
import com.jmortegaf.bms.dtos.CashRegisterPeriodDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cash_register")
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String period;
    private Integer initialBalance;
    private Integer finalBalance;
    private Boolean active;

    @OneToMany(mappedBy = "registerPeriod", cascade = CascadeType.PERSIST)
    private List<CashRegisterEntry> entries;

    public CashRegister(YearMonth period, Integer initialBalance){
        this.period = period.format(DateTimeFormatter.ofPattern("MM/yyyy"));
        this.initialBalance = initialBalance;
        this.finalBalance = initialBalance;
        this.active = true;
    }

    public CashRegister(@Valid CashRegisterPeriodDTO cashRegisterPeriodDTO) {
        period = cashRegisterPeriodDTO.period();
        initialBalance = cashRegisterPeriodDTO.initialBalance();
        finalBalance= cashRegisterPeriodDTO.finalBalance();
        active=true;
    }

    public void addEntry(@Valid CashRegisterEntryDTO entry) {
        entries.add(new CashRegisterEntry(this,entry));
        if(entry.entryType()==EntryType.PAYMENT)finalBalance+=entry.amount();
        else if(entry.entryType()==EntryType.CHARGE)finalBalance-=entry.amount();
    }

    public void addEntry(Quote quote) {
        entries.add(new CashRegisterEntry(this,quote));
        finalBalance += quote.getTotal();
    }

    public Integer getPayments(){
        return entries.stream()
                .filter(entry -> entry.getEntryType() == EntryType.PAYMENT)
                .mapToInt(CashRegisterEntry::getAmount)
                .sum();
    }

    public Integer getCharges(){
        return entries.stream()
                .filter(entry -> entry.getEntryType() == EntryType.CHARGE)
                .mapToInt(CashRegisterEntry::getAmount)
                .sum();
    }

    @Override
    public String toString() {
        return "CashRegister{" +
                "period='" + period + '\'' +
                ", initialBalance=" + initialBalance +
                ", finalBalance=" + finalBalance +
                '}';
    }

    public void recalculateTotal() {
        Integer payments = entries.stream()
                .filter(entry -> entry.getEntryType() == EntryType.PAYMENT)
                .mapToInt(CashRegisterEntry::getAmount)
                .sum();
        Integer charges = entries.stream()
                .filter(entry -> entry.getEntryType() == EntryType.CHARGE)
                .mapToInt(CashRegisterEntry::getAmount)
                .sum();
        finalBalance = initialBalance + payments - charges;
    }
}
