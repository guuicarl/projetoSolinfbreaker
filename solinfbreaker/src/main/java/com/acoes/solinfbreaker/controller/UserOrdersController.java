package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserOrdersController {

    @Autowired
    private UserOrdersRepository userOrdersRepository;

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }


}
