package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.NewItemDTO;
import com.jmortegaf.bms.dtos.UpdateItemDTO;
import com.jmortegaf.bms.models.InventoryItem;
import com.jmortegaf.bms.repositories.InventoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public ResponseEntity<?> getAllInventoryItems() {
        List<InventoryItem> items = inventoryRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return ResponseEntity.ok(items.stream().map(NewItemDTO::new).toList());

    }

    public ResponseEntity<?> addItemToInventory(@Valid NewItemDTO itemDTO) {
        InventoryItem inventoryItem = new InventoryItem(itemDTO);
        inventoryRepository.save(inventoryItem);
        return ResponseEntity.ok("New Item Added");
    }

    public ResponseEntity<?> getItem(Long id) {
        InventoryItem inventoryItem=inventoryRepository.getReferenceById(id);
        return ResponseEntity.ok(new NewItemDTO(inventoryItem));
    }

    @Transactional
    public ResponseEntity<?> updateItem(Long id, @Valid UpdateItemDTO itemDTO) {
        InventoryItem inventoryItem = inventoryRepository.getReferenceById(id);
        inventoryItem.update(itemDTO);
        return ResponseEntity.ok("Item updated");
    }
}
