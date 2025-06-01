package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.ClientDTO;
import com.jmortegaf.bms.models.Client;
import com.jmortegaf.bms.repositories.ClientsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ResponseEntity<?> getClients() {
        List<Client> clients = clientsRepository.findAll();
        System.out.println(clients);
        return ResponseEntity.ok(clients.stream().map(ClientDTO::new).toList());
    }

    public ResponseEntity<?> newClient(@Valid ClientDTO clientData) {
        Client client = new Client(clientData);
        clientsRepository.save(client);
        return ResponseEntity.ok("Client added");
    }

    public ResponseEntity<?> getClient(@Valid Long id) {
        Client client = clientsRepository.getReferenceById(id);
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Transactional
    public ResponseEntity<?> updateClient(@Valid Long id, @Valid ClientDTO clientData) {
        Client client = clientsRepository.getReferenceById(id);
        client.update(clientData);
        return ResponseEntity.ok("Client data updated");
    }
}
