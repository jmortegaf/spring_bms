package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
