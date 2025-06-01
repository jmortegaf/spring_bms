package com.jmortegaf.bms.models;

import com.jmortegaf.bms.dtos.CashRegisterEntryDTO;
import com.jmortegaf.bms.dtos.JobDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cash_register_entry")
public class CashRegisterEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String description;
    private EntryType entryType;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "cash_register_id", nullable = false)
    private CashRegister registerPeriod;

    @OneToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    public CashRegisterEntry(CashRegister period, @Valid CashRegisterEntryDTO entry) {
        registerPeriod = period;
        date = entry.date();
        description = entry.description();
        entryType = entry.entryType();
        amount = entry.amount();
    }

    public CashRegisterEntry(@Valid JobDTO jobData) {

    }

    public CashRegisterEntry(CashRegister period, Quote quote) {
        registerPeriod = period;
        date = quote.getDate();
        description = quote.getDescription();
        entryType = EntryType.PAYMENT;
        amount = quote.getTotal();
        this.quote = quote;
    }

    public void recalculateTotal(Integer subTotal) {
        amount = subTotal;
        registerPeriod.recalculateTotal();
    }

    @Override
    public String toString() {
        return "CashRegisterEntry{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", entryType=" + entryType +
                '}';
    }

}
