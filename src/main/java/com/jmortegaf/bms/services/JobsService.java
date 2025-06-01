package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.JobDTO;
import com.jmortegaf.bms.dtos.JobDetailsDTO;
import com.jmortegaf.bms.models.CashRegisterEntry;
import com.jmortegaf.bms.models.Client;
import com.jmortegaf.bms.models.Job;
import com.jmortegaf.bms.repositories.CashRegisterRepository;
import com.jmortegaf.bms.repositories.ClientsRepository;
import com.jmortegaf.bms.repositories.JobsRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;
    private final ClientsRepository clientsRepository;
    private final CashRegisterRepository cashRegisterRepository;

    public JobsService(JobsRepository jobsRepository,
                       ClientsRepository clientsRepository,
                       CashRegisterRepository cashRegisterRepository) {
        this.jobsRepository = jobsRepository;
        this.clientsRepository = clientsRepository;
        this.cashRegisterRepository = cashRegisterRepository;
    }

    public ResponseEntity<?> getAllJobs() {
        List<Job> jobs = jobsRepository.findAll(Sort.by(Sort.Direction.DESC,"date"));
        return ResponseEntity.ok(jobs.stream().map(JobDetailsDTO::new).toList());
    }

    public ResponseEntity<?> newJob(@Valid JobDTO jobData) {
        Client client = clientsRepository.getReferenceById(jobData.clientId());
        if(jobData.paymentState()){
            CashRegisterEntry cashRegisterEntry = new CashRegisterEntry(jobData);
        }
        Job job = new Job(jobData, client);
        jobsRepository.save(job);
        return ResponseEntity.ok("New service created");
    }
}
