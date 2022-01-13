package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepository extends JpaRepository<UserOrders, Long> {

}
