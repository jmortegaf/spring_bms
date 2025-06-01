package com.jmortegaf.bms.models;

import com.jmortegaf.bms.dtos.ClientDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String name;
    private String activity;
    private String address;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Job> jobs;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Quote> quotes;

    public Client(@Valid ClientDTO clientData) {
        dni = clientData.dni();
        name = clientData.name();
        activity = clientData.activity();
        address = clientData.address();
        email = clientData.email();
    }

    public void update(@Valid ClientDTO clientData) {
        dni = clientData.dni() != null ? clientData.dni() : dni;
        name = clientData.name() != null ? clientData.name() : name;
        activity = clientData.activity() != null ? clientData.activity() : activity;
        address = clientData.address() != null ? clientData.address() : address;
        email = clientData.email() != null ? clientData.email() : email;

    }
}
