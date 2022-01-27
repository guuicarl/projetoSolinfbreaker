package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.dto.UserStockDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
public class UserOrdersController {

    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrders> salvar(@RequestBody UserOrdersDto dto) {
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        Double dollar = user.getDollar_balance();
        Double mult = dto.getPrice() * dto.getVolume();
        if(dollar >= mult) {
            UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto1(user));
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
            List<UserOrders> userStatus = userOrdersRepository.findByStatus();
            List<UserOrders> userStock = userOrdersRepository.findStockExists();
            List<UserOrders> novoTeste = userOrdersRepository.novoTeste();
            if (userOrders.isEmpty()){
                List<UserOrders> userFind = userOrdersRepository.findByCalculo();
                System.out.println(dto.getStatus());
                if(!userFind.isEmpty() ){

                    for (UserOrders cont: userFind) {//Esse for lista os matches
                        userOrdersRepository.updateRemainingValue(cont);
                        userOrdersRepository.updateStatus(cont);
                        userOrdersRepository.atualizarBalance(cont.getUser(), cont.getId_stock());
                        }
                }
                if (!userStatus.isEmpty()){//Atualiza o dollar ballance
                    for (UserOrders cont: userStatus) {
                        userOrdersRepository.updateDollarBalance(cont.getUser());
                        userOrdersRepository.updateStatus2(cont);

                    }
                }


//                if(userStock.isEmpty()){
//                    userOrdersRepository.inserirStock();
//                } else if(!userStock.isEmpty()) {
//                    System.out.println("testeasasasasasassasasas");
//                   // update caso compra
//                }

            }
        }
        return null;
    }




//if(dtousuario.dollarbalance >= dto.getPrice * dto.getVolume())
}
