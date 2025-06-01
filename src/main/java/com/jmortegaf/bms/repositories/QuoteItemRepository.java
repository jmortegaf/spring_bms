package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.QuoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteItemRepository extends JpaRepository<QuoteItem, Long> {
}
