package com.jmortegaf.bms.repositories;

import com.jmortegaf.bms.models.Client;
import com.jmortegaf.bms.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByClient(Client client);
}
