package com.jmortegaf.bms.dtos;

import com.jmortegaf.bms.models.InventoryItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewItemDTO(
        Long id,
        @NotBlank String name,
        @NotNull Integer stock,
        Integer buyPrice,
        Integer shipmentCost,
        Integer importTaxes,
        Float utility,
        Integer price,
        String itemLink) {
    public NewItemDTO(InventoryItem inventoryItem){
        this(inventoryItem.getId(),
                inventoryItem.getName(),
                inventoryItem.getStock(),
                inventoryItem.getBuyPrice(),
                inventoryItem.getShipmentCost(),
                inventoryItem.getImportTaxes(),
                inventoryItem.getUtility(),
                inventoryItem.getPrice(),
                inventoryItem.getItemLink());
    }
}
