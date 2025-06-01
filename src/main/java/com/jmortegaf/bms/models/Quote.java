package com.jmortegaf.bms.models;

import com.jmortegaf.bms.dtos.AddItemToQuoteDTO;
import com.jmortegaf.bms.dtos.NewQuoteDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private QuoteState state;

    private Integer subTotal;
    private Integer tax;
    private Integer total;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuoteItem> quoteItems;

    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    private CashRegisterEntry cashRegisterEntry;

    public Quote(@Valid NewQuoteDTO newQuoteDTO, Client client) {
        date = newQuoteDTO.date();
        description = newQuoteDTO.description();
        this.client = client;
        state=QuoteState.OPENED;
        quoteItems = new ArrayList<>();
    }

    public void addItem(InventoryItem item, Integer quantity) {
        quoteItems.add(new QuoteItem(this, item, quantity));

    }
    public void removeItem(QuoteItem quoteItem) {
        quoteItems.remove(quoteItem);
    }
    public void recalculateTotal() {
        subTotal= quoteItems.stream()
                .mapToInt(item -> item.getQuantity()* item.getPricePerUnit())
                .sum();
        tax = (int) (subTotal * 0.19);
        total = subTotal + tax;
        if(cashRegisterEntry!=null)
            cashRegisterEntry.recalculateTotal(subTotal);
    }


    @Override
    public String toString() {
        return "Quote{" +
                "clientName='" + client.getName() + '\'' +
                ", state=" + state +
                ", total=" + total +
                '}';
    }

}
