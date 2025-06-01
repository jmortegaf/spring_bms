package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.CashRegisterEntryDTO;
import com.jmortegaf.bms.dtos.CashRegisterPeriodDTO;
import com.jmortegaf.bms.services.CashRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cash-register")
public class CashRegisterController {

    private final CashRegisterService cashRegisterService;

    public CashRegisterController(CashRegisterService cashRegisterService) {
        this.cashRegisterService = cashRegisterService;
    }
    @GetMapping("/get-last-periods")
    public ResponseEntity<?> getLastPeriods(){
        return cashRegisterService.getLastPeriods();
    }

    @PostMapping("/close-period")
    public ResponseEntity<?> closePeriod(){
        return cashRegisterService.closePeriod();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeriod(@PathVariable @Valid Long id){
        return cashRegisterService.getPeriod(id);
    }

    @PostMapping()
    public ResponseEntity<?> addEntry(@RequestBody @Valid CashRegisterEntryDTO entry){
        return cashRegisterService.addEntry(entry);
    }

    @GetMapping("/active-period")
    public ResponseEntity<?> getActivePeriod(){
        return  cashRegisterService.getActivePeriod();
    }




}
