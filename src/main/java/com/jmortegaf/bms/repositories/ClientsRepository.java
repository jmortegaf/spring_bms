package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {
}
