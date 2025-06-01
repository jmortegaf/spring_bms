package com.jmortegaf.bms.models;

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
@Entity(name = "services")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private LocalDate date;
    private String description;
    private Integer price;
    private Boolean paymentState;

    public Job(@Valid JobDTO jobData, Client client) {
        id = jobData.id();
        this.client = client;
        date = jobData.date();
        description = jobData.description();
        price = jobData.price();
        paymentState = jobData.paymentState();
    }
}
