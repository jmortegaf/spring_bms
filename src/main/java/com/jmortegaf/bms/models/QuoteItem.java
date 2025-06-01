package com.jmortegaf.bms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quote_item")
public class QuoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private InventoryItem item;

    private Integer quantity;
    private Integer pricePerUnit;


    public QuoteItem(Quote quote, InventoryItem item, Integer quantity) {
        this.quote = quote;
        this.item = item;
        pricePerUnit= item.getPrice();
        this.quantity = quantity;
    }
}
