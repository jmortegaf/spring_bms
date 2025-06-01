package com.jmortegaf.bms.controllers;

import com.jmortegaf.bms.dtos.AddItemToQuoteDTO;
import com.jmortegaf.bms.dtos.NewQuoteDTO;
import com.jmortegaf.bms.services.QuoteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<?> getQuotes(){
        return quoteService.getQuotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuote(@PathVariable @Valid Long id){
        return quoteService.getQuote(id);
    }

    @PostMapping("/new-quote")
    public ResponseEntity<?> newQuote(@RequestBody @Valid NewQuoteDTO quoteData){
        return quoteService.newQuote(quoteData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addItemToQuote(@PathVariable @Valid Long id,
                                            @RequestBody @Valid AddItemToQuoteDTO addItemToQuoteDTO){
        return quoteService.addItemToQuote(id, addItemToQuoteDTO);
    }
    @DeleteMapping("/remove-item/{id}")
    public ResponseEntity<?> removeItemFromQuote(@PathVariable @Valid Long id){
        return quoteService.removeItemFromQuote(id);
    }

    @PostMapping("/validate/{id}")
    public ResponseEntity<?> validateQuote(@PathVariable @Valid Long id){
        return quoteService.validateQuote(id);
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<?> payQuote(@PathVariable @Valid Long id){
        return quoteService.payQuote(id);
    }



}
