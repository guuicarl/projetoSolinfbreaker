package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class UserStockBalancesController {

    @Autowired
    private UserStockBalancesRepository userStockBalancesRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private WebClient webClient;

    public List<UserStockBalances> listar(){
        return userStockBalancesRepository.findAll();
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<UserStockBalances> obterPorCodigo(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        UserStockBalances userStockBalances = this.stockService.obterPorCodigo(id, token);

        return ResponseEntity.ok(userStockBalances);
    }
}
