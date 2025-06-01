package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.JobDTO;
import com.jmortegaf.bms.services.JobsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class JobsController {

    private final JobsService jobsService;

    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJobs(){
        return jobsService.getAllJobs();
    }

    @PostMapping("new-job")
    public ResponseEntity<?> newJob(@RequestBody @Valid JobDTO job){
        return jobsService.newJob(job);
    }


}
