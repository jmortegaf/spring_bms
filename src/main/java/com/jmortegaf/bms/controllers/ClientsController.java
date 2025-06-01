package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.ClientDTO;
import com.jmortegaf.bms.services.ClientsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getClients(){
        return clientsService.getClients();
    }

    @PostMapping("/new-client")
    public ResponseEntity<?> newClient(@RequestBody @Valid ClientDTO client){
        return clientsService.newClient(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable @Valid Long id){
        return clientsService.getClient(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable @Valid Long id, @RequestBody @Valid ClientDTO client){
        return clientsService.updateClient(id, client);
    }
}
