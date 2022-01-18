package com.acoes.solinfbreaker.controller;


import com.acoes.solinfbreaker.model.Stocks;
import com.acoes.solinfbreaker.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
