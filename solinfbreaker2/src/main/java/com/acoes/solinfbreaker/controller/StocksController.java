package com.acoes.solinfbreaker.controller;


import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.model.Stocks;
import com.acoes.solinfbreaker.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StocksController {
    @Autowired
    private StocksRepository stocksRepository;

    @GetMapping("/stocks/{id}")
    public Optional<Stocks> obterStock(@PathVariable(value = "id")Long id) throws Exception {
        Thread.sleep(3000);
        return stocksRepository.findById(id);
    }

    @GetMapping("/stocks")
    public List<Stocks> listar(){
        return stocksRepository.findAll();
    }

    @PostMapping("/teste")
    public ResponseEntity<?> teste(@RequestBody StockDto stockDto){
        Stocks stock = stocksRepository.findById(stockDto.getId()).orElseThrow();
        if(stockDto.getAsk_max() != null) {
            stock.setAsk_max(stockDto.getAsk_max());
            stock.setAsk_min(stockDto.getAsk_min());
        }
        if (stockDto.getBid_min() != null) {
            stock.setBid_max(stockDto.getBid_max());
            stock.setBid_min(stockDto.getBid_min());
        }
        return new ResponseEntity<>(stocksRepository.save(stock), HttpStatus.CREATED);
    }

}
