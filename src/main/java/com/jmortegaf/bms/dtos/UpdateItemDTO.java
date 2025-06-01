package com.jmortegaf.bms.dtos;

import com.jmortegaf.bms.models.InventoryItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateItemDTO(
        String name,
        Integer stock,
        Integer price,
        String itemLink) {}
