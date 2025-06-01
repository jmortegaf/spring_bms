package com.jmortegaf.bms.services;

import com.jmortegaf.bms.dtos.AddItemToQuoteDTO;
import com.jmortegaf.bms.dtos.NewQuoteDTO;
import com.jmortegaf.bms.dtos.QuoteDTO;
import com.jmortegaf.bms.models.*;
import com.jmortegaf.bms.repositories.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final InventoryRepository inventoryRepository;
    private final QuoteItemRepository quoteItemRepository;

    @Autowired
    private CashRegisterRepository cashRegisterRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    public QuoteService(QuoteRepository quoteRepository, InventoryRepository inventoryRepository,
                        QuoteItemRepository quoteItemRepository) {
        this.quoteRepository = quoteRepository;
        this.inventoryRepository = inventoryRepository;
        this.quoteItemRepository = quoteItemRepository;
    }

    public ResponseEntity<?> getQuote(@Valid Long id) {
        Quote quote = quoteRepository.getReferenceById(id);
        return ResponseEntity.ok(new QuoteDTO(quote));
    }

    public ResponseEntity<?> newQuote(@Valid NewQuoteDTO quoteData) {
        Client client = clientsRepository.getReferenceById(quoteData.clientId());
        Quote quote = new Quote(quoteData, client);
        quoteRepository.save(quote);

        return ResponseEntity.ok(Map.of("quoteId",quote.getId()));
    }

    @Transactional
    public ResponseEntity<?> addItemToQuote(@Valid Long id,
                                            @Valid AddItemToQuoteDTO addItemToQuoteDTO) {
        Quote quote = quoteRepository.getReferenceById(id);
        InventoryItem item = inventoryRepository.getReferenceById(addItemToQuoteDTO.itemId());

        quote.addItem(item,addItemToQuoteDTO.quantity());
        quote.recalculateTotal();

        return ResponseEntity.ok("Item added to quote");
    }

    @Transactional
    public ResponseEntity<?> validateQuote(@Valid Long id) {
        Quote quote = quoteRepository.getReferenceById(id);
        quote.setState(QuoteState.CLOSED);
        return ResponseEntity.ok("Quote closed");
    }

    @Transactional
    public ResponseEntity<?> payQuote(@Valid Long id) {
        Quote quote = quoteRepository.getReferenceById(id);
        quote.setState(QuoteState.PAID);

        CashRegister cashRegister = cashRegisterRepository.findByActiveTrue();
        System.out.println(cashRegister);
        cashRegister.addEntry(quote);
        return ResponseEntity.ok("Quote paid");
    }

    @Transactional
    public ResponseEntity<?> removeItemFromQuote(@Valid Long id) {
        QuoteItem quoteItem = quoteItemRepository.getReferenceById(id);
        Quote quote = quoteItem.getQuote();
        quote.removeItem(quoteItem);
        quote.recalculateTotal();

        return ResponseEntity.ok("Item removed from quote");
    }

    public ResponseEntity<?> getQuotes() {
        var pageData = quoteRepository.findAllByOrderByDateDesc();
        return ResponseEntity.ok(
                pageData.stream()
                        .map(QuoteDTO::new)
                        .toList()
                        );
    }
}
