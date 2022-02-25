package com.acoes.solinfbreaker.controller;

//import com.acoes.solinfbreaker.model.Stock;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WebClient webClient;

    @GetMapping("/users")
    public List<User> listar(){
        return usersRepository.findAll();
    }

    @GetMapping("/u/{username}")
    public Long lista(@PathVariable ("username") String username){
        Optional<User> us = usersRepository.findByUser(username);
        if(us.isEmpty()){
            return usersRepository.save(new User(username, "teste1234", 10000.00)).getId();
        }else {
            return us.get().getId();
        }
    }

    @GetMapping("/user/{id}")
    public Double lista1(@PathVariable ("id") Long id){
        Optional<User> us = usersRepository.findById(id);
        return us.get().getDollar_balance();
    }


//            return ResponseEntity.ok().body(service.getUser(id_user));

    /*@GetMapping
    public UserStockBalances obterPorCodigo(Long id,@RequestHeader("Authorization") String token ) {
        Mono<UserStockBalances> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(UserStockBalances.class);

        monoStock.subscribe(s -> {
            System.out.println("acabou");
        });
        UserStockBalances stock = monoStock.block();
        return stock;
    }
    @GetMapping("/stocks")
    public List<UserStockBalances> listar2(@RequestHeader("Authorization") String token){
        Mono<UserStockBalances[]> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(UserStockBalances[].class);
        return Arrays.stream(monoStock.block()).toList();

    }*/

    @PostMapping("/users")
    public User adicionar(@RequestBody User user) {
        return usersRepository.save(user);

    }

}
