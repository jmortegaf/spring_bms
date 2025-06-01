package com.jmortegaf.bms.models;

import com.jmortegaf.bms.dtos.NewItemDTO;
import com.jmortegaf.bms.dtos.UpdateItemDTO;
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
@Entity(name = "inventory_item")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer stock;
    private Integer buyPrice;
    private Integer shipmentCost;
    private Integer importTaxes;
    private Float utility;
    private Integer price;
    private String itemLink;

    // Quote
    @OneToMany(mappedBy = "item")
    private List<QuoteItem> quoteItems;



    public InventoryItem(@Valid NewItemDTO itemDTO) {
        name=itemDTO.name();
        stock= itemDTO.stock();
        buyPrice = itemDTO.buyPrice();
        shipmentCost = itemDTO.shipmentCost();
        importTaxes = itemDTO.importTaxes();
        utility = itemDTO.utility();
        price= itemDTO.price();
        itemLink=itemDTO.itemLink();
    }

    public void update(@Valid UpdateItemDTO itemDTO) {
        name = itemDTO.name() != null ? itemDTO.name() : name;
        stock = itemDTO.stock() != null ? itemDTO.stock() : stock;
        price = itemDTO.price() != null ? itemDTO.price() : price;
        itemLink = itemDTO.itemLink() != null ? itemDTO.itemLink() : itemLink;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
