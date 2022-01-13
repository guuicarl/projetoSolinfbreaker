package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserStockBalancesController {

    @Autowired
    private UserStockBalancesRepository userStockBalancesRepository;

    public List<UserStockBalances> listar(){
        return userStockBalancesRepository.findAll();
    }
}
