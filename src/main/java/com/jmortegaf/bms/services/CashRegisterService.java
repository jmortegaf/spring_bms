package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.CashRegisterEntryDTO;
import com.jmortegaf.bms.dtos.CashRegisterPeriodDTO;
import com.jmortegaf.bms.models.CashRegister;
import com.jmortegaf.bms.models.CashRegisterEntry;
import com.jmortegaf.bms.repositories.CashRegisterRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CashRegisterService {

    private final CashRegisterRepository cashRegisterRepository;

    public CashRegisterService(CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }

    @Transactional
    public ResponseEntity<?> closePeriod() {
        CashRegister prevCashRegisterPeriod = cashRegisterRepository.findByActiveTrue();
        prevCashRegisterPeriod.setActive(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth prevPeriod = YearMonth.parse(prevCashRegisterPeriod.getPeriod(),formatter)
                .plusMonths(1);
        CashRegister newCashRegisterPeriod = new CashRegister(prevPeriod, prevCashRegisterPeriod.getFinalBalance());
        cashRegisterRepository.save(newCashRegisterPeriod);

        return ResponseEntity.ok("Period closed");
    }

    public ResponseEntity<?> createNewPeriod(@Valid CashRegisterPeriodDTO cashRegisterPeriodDTO) {
        CashRegister prevPeriod = cashRegisterRepository.findByActiveTrue();
        CashRegister cashRegister = new CashRegister(cashRegisterPeriodDTO);
        cashRegisterRepository.save(cashRegister);
        return ResponseEntity.ok(new CashRegisterPeriodDTO(cashRegister));

    }

    public ResponseEntity<?> getPeriod(@Valid Long id) {
        CashRegister cashRegister = cashRegisterRepository.getReferenceById(id);
        return ResponseEntity.ok(new CashRegisterPeriodDTO(cashRegister));
    }

    public ResponseEntity<?> getLastPeriods() {
        List<CashRegister> periods = cashRegisterRepository.findAll();
        return ResponseEntity.ok(
                periods.stream().skip(Math.max(0,periods.size()-6))
                        .map(CashRegisterPeriodDTO::new).toList());
    }

    @Transactional
    public ResponseEntity<?> addEntry(@Valid CashRegisterEntryDTO entry) {
        CashRegister cashRegister = cashRegisterRepository.findByActiveTrue();
        cashRegister.addEntry(entry);
        return ResponseEntity.ok("Entry added");
    }

    public ResponseEntity<?> getActivePeriod() {
        CashRegister cashRegister = cashRegisterRepository.findByActiveTrue();
        return ResponseEntity.ok(new CashRegisterPeriodDTO(cashRegister));
    }

}
