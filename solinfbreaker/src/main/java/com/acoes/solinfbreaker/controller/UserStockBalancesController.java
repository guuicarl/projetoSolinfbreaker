package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.UserStockDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.StockService;
import com.acoes.solinfbreaker.service.UserStockBalanceService;
import com.acoes.solinfbreaker.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin
@RestController
public class UserStockBalancesController {

    @Autowired
    private UserStockBalancesRepository userStockBalancesRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private WebClient webClient;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserStockService userStockService;
    @Autowired
    private UserStockBalanceService service;

    @GetMapping("/teste")
    public List<UserStockBalances> listar(){
        return userStockBalancesRepository.findAll();
    }

//    @GetMapping("/stocks/{id}")
//    public ResponseEntity<UserStockDto> obterPorCodigo(@PathVariable Long id, @RequestHeader("Authorization") String token) {
//
//        UserStockDto userStockBalances = this.stockService.obterPorCodigo(id, token);
//
//        return ResponseEntity.ok(userStockBalances);
//    }

    @GetMapping("/teste/{id}")
    public ResponseEntity<StockDto> obterPorCodigo2(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        StockDto userStockBalances = this.stockService.obterPorCodigo2(id, token);

        return ResponseEntity.ok(userStockBalances);
    }

    @PostMapping("/")
    public ResponseEntity<UserStockBalances> salvar(@RequestBody UserStockDto dto){
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        UserStockBalances userStockBalances = userStockService.salvar(dto.tranformaParaObjeto(user));
        return new ResponseEntity<>(userStockBalances,HttpStatus.CREATED);
        //UserStockBalances userStockBalances = dto.tranformaParaObjeto(user);

    }

    @GetMapping("/wallet")
    public List<UserStockBalances> listarCarteira(){
        return  userStockBalancesRepository.listCarteira();
    }

    @GetMapping("/{stock_name}")
    public ResponseEntity<List<UserStockBalances>> getBalance(@PathVariable("stock_name") String stock_name) {
        try {
            return ResponseEntity.ok().body(service.getStock(stock_name));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }


//    @PostMapping("/")
//    public ResponseEntity<UserStockBalances> salvar(@RequestBody UserStockDto dto){
//        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
//        UserStockBalances userStockBalances = userStockService.salvar(dto.tranformaParaObjeto(user));
//        return new ResponseEntity<>(userStockBalances,HttpStatus.CREATED);
//        //UserStockBalances userStockBalances = dto.tranformaParaObjeto(user);
//
//    }
}
