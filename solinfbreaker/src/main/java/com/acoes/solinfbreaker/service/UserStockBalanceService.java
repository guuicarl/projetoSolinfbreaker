package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStockBalanceService {
    private final UserStockBalancesRepository repository;
    public List<UserStockBalances> getStock(String stock_name) throws Exception {
        List<UserStockBalances> userStockBalances = repository.listCarteira2(stock_name);
        return userStockBalances;
    }
}
