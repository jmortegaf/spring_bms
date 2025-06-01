package com.jmortegaf.bms.dtos;

import com.jmortegaf.bms.models.QuoteItem;

public record QuoteItemDTO(
        Long itemId,
        String itemName,
        Integer pricePerUnit,
        Integer quantity) {
    public QuoteItemDTO(QuoteItem quoteItem) {
        this(quoteItem.getItem().getId(), quoteItem.getItem().getName() , quoteItem.getPricePerUnit(), quoteItem.getQuantity());
    }
}
