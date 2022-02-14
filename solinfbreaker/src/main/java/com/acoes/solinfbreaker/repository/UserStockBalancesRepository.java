package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStockBalancesRepository extends JpaRepository<UserStockBalances, UserStockBalance> {
    @Query(value = "select * from user_stock_balances where id_user=1", nativeQuery = true)
    List<UserStockBalances> listCarteira();

    @Query(value = "select * from user_stock_balances where id_user=1 and stock_name = ?1", nativeQuery = true)
    List<UserStockBalances> listCarteira2(String stock_name);

}
