package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.NewItemDTO;
import com.jmortegaf.bms.dtos.UpdateItemDTO;
import com.jmortegaf.bms.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // ENDPOINTS

    @GetMapping("/all")
    public ResponseEntity<?> getAllInventoryItems(){
        return inventoryService.getAllInventoryItems();
    }

    @PostMapping("/add-item")
    public ResponseEntity<?> addItemToInventory(@RequestBody @Valid NewItemDTO itemDTO){
        return inventoryService.addItemToInventory(itemDTO);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody @Valid UpdateItemDTO itemDTO){
        return inventoryService.updateItem(id, itemDTO);
    }

    @GetMapping("/get-item/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id){
        return inventoryService.getItem(id);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testAPI(){
        return ResponseEntity.ok("Test was ok");
    }

}
