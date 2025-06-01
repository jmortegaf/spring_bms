package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
    CashRegister findByActiveTrue();
}
