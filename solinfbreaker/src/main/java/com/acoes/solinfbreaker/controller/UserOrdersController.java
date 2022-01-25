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

    @PostMapping("/compra")
     public UserOrders comprar(@RequestBody UserOrdersDto dto) throws SQLException {

        if(dto.getType() == 0){
            List<UserOrders> userOrders =userOrdersRepository.findByTypeStock(dto.getId_stock());
            List<UserOrders> userStatus = userOrdersRepository.findByStatus();
            if (userOrders != null){
                List<UserOrders> userFind = userOrdersRepository.findByCalculo();
//                List<UserOrders> userStatus = userOrdersRepository.findByStatus();
                System.out.println(dto.getStatus());
                if(!userFind.isEmpty() ){
                    System.out.println("cheguei");

                    for (UserOrders cont: userFind) {
                        userOrdersRepository.updateRemainingValue(cont);
                        userOrdersRepository.updateStatus(cont);
                        }
                }
                if (!userStatus.isEmpty()){
                    System.out.println("Cheguei");
                    for (UserOrders cont: userStatus) {
                        System.out.println("novo");
                        userOrdersRepository.updateDollarBalance(cont.getUser());
                        userOrdersRepository.updateStatus2(cont);
                    }
                }

            }
        }
        return null;
    }




//if(dtousuario.dollarbalance >= dto.getPrice * dto.getVolume())
}
