package com.jmortegaf.bms.dtos;

import com.jmortegaf.bms.models.Client;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        Long id,
        @NotBlank String name,
        @NotBlank String dni,
        String activity,
        String address,
        String email) {
    public ClientDTO(Client client){
        this(client.getId(), client.getName(), client.getDni(), client.getActivity(), client.getAddress(), client.getEmail());
    }
}
