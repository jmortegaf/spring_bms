package com.jmortegaf.bms.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jmortegaf.bms.models.Client;
import com.jmortegaf.bms.models.Quote;
import com.jmortegaf.bms.models.QuoteItem;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public record QuoteDTO(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull LocalDate date,
        String description,
        ClientDTO client,
        Integer subTotal,
        Integer tax,
        Integer total,
        List<QuoteItemDTO> quoteItems) {

    public QuoteDTO(Quote quote) {
        this(
                quote.getId(),
                quote.getDate(),
                quote.getDescription(),
                new ClientDTO(quote.getClient()),
                quote.getSubTotal(),
                quote.getTax(),
                quote.getTotal(),
                quote.getQuoteItems().stream()
                        .sorted(Comparator.comparing(QuoteItem::getId))
                        .map(QuoteItemDTO::new).toList().reversed());
    }
}
