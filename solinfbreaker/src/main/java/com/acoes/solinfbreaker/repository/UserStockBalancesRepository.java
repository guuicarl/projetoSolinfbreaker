package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockBalancesRepository extends JpaRepository<UserStockBalances, UserStockBalance> {
}
