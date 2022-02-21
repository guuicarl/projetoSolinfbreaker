package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.CompraRepository;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.StockService;
import com.acoes.solinfbreaker.service.UserOrderService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.*;
import java.util.List;

@CrossOrigin
@RestController
public class UserOrdersController {

    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private WebClient webClient;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserOrderService userOrderService;

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }

    @GetMapping("/uo/{id_user}")
    public ResponseEntity<List<UserOrders>> getUser(@PathVariable("id_user") Long id_user) {
        try {
            return ResponseEntity.ok().body(userOrderService.getUser(id_user));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrders> salvar(@RequestBody UserOrdersDto dto ,@RequestHeader("Authorization") String token) {
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        Double dollar = user.getDollar_balance();
        Double mult = dto.getPrice() * dto.getVolume();
        if(dollar >= mult) {//verifica se o usuario tem dinheiro na carteira pra criar uma ordem de compra
            UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto1(user));
            stockService.teste1(userOrders.getId_stock(), token);
            userOrderService.vender();
            return new ResponseEntity<>(userOrders, HttpStatus.CREATED);
        } else {
            System.out.println("Ordem não criada, valor insuficiente");
        }
        return null;
    }

    @PostMapping("/teste/{id}")
    public ResponseEntity<StockDto> teste(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception {
        StockDto stockDto1 = this.stockService.teste1(id, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockDto1);
    }
//    public ResponseEntity<StockDto> teste1(@RequestBody StockDto dto, Long id, @RequestHeader("Authorization") String token) {
////        JSONObject json = new JSONObject();
////        json.put("id_stock", userOrdersRepository.findById(dto.getId()));
////        json.put("ask_min", userOrdersRepository.getAskMin());
////        json.put("ask_max", userOrdersRepository.getAskMax());
////        json.put("bid_min", userOrdersRepository.getBidMin());
////        json.put("bid_max", userOrdersRepository.getBidMax());
//
//        StockDto stockDto = new StockDto(3L, "teste3", "teste3", 5.0, 6.0, 7.0, 8.0, null, null);
//        Mono<StockDto> monoStock =
//                this.webClient
//                        .put()
//                        .uri("/teste/{id}", id)
//                        .body(BodyInserters.fromValue(stockDto))
//                        .retrieve()
//                        .bodyToMono(StockDto.class);
//        StockDto stock = monoStock.block();
//        return ResponseEntity.status(HttpStatus.CREATED).body(stockDto);
//    }

}
