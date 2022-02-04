package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.repository.CompraRepository;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.StockService;
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

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrders> salvar(@RequestBody UserOrdersDto dto ,@RequestHeader("Authorization") String token) {
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        Double dollar = user.getDollar_balance();
        Double mult = dto.getPrice() * dto.getVolume();
        if(dollar >= mult) {//verifica se o usuario tem dinheiro na carteira pra criar uma ordem de compra
            UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto1(user));
            stockService.teste1(userOrders.getId_stock(), token);
            return new ResponseEntity<>(userOrders, HttpStatus.CREATED);
        } else {
            System.out.println("Ordem não criada, valor insuficiente");
        }
        return null;
    }

    @PostMapping("/venda")
     public UserOrders vender(@RequestBody UserOrdersDto dto) throws SQLException {

        if(dto.getType() == 0){
            List<UserOrders> userOrders =userOrdersRepository.findByTypeStock(dto.getId_stock());
            List<UserOrders> teste1 = userOrdersRepository.testando1();
            List<UserOrders> userteste= compraRepository.fyndteste();
            List<UserOrders> userteste1= compraRepository.findtTeste1();
            List<UserOrders> userFind = userOrdersRepository.findByCalculo();
            if (userOrders.isEmpty()){
                System.out.println(dto.getStatus());
                if(!userteste1.isEmpty()) {
                    System.out.println("compra negativa");
                    for (UserOrders cont:userteste1)  {
                        System.out.println(dto.getId());
                        compraRepository.updateDollarBalanceNE(cont, cont.getUser());
                        compraRepository.RemainingNE(cont);
                        compraRepository.atualizarBalanceNE(cont.getId(),cont.getUser(), cont.getId_stock());
                    }
                    //userOrdersRepository.updateStatus2();
                }
                if(!userFind.isEmpty() ){
                    System.out.println("venda positiva");
                    for (UserOrders cont: userFind) {
                        userOrdersRepository.updateDollarBalance(cont.getUser());
                        userOrdersRepository.updateRemainingValue(cont.getId_stock(),cont);
                        userOrdersRepository.atualizarBalance(cont.getUser(), cont.getId_stock());
                    }
                }
                if(!userteste.isEmpty()){
                    System.out.println("compra positiva");
                    for ( UserOrders cont: userteste ) {
                        compraRepository.updateDollarBalancePO(cont.getUser(), cont);
                        compraRepository.RemainigPO(cont, cont.getUser());
                        compraRepository.atualizarBalancePO(cont.getId(), cont.getUser(), cont.getId_stock());
                    }
                    userOrdersRepository.updateStatus();
                }
                if (!teste1.isEmpty()){
                    System.out.println("venda negativa");
                    for (UserOrders cont: teste1) {
                        userOrdersRepository.RemainingNE(cont.getUser(), cont.getId_stock());
                        userOrdersRepository.updateDollarBalanceNE(cont, cont.getUser());
                        userOrdersRepository.updateRemainingValue2(cont);
                    }
                }
                userOrdersRepository.updateStatus2();
            }
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
