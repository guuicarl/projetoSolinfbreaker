package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.dto.UserStockDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.repository.CompraRepository;
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
    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrders> salvar(@RequestBody UserOrdersDto dto) {
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        Double dollar = user.getDollar_balance();
        Double mult = dto.getPrice() * dto.getVolume();
        if(dollar >= mult) {//verifica se o usuario tem dinheiro na carteira pra criar uma ordem de compra
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
            List<UserOrders> teste1 = userOrdersRepository.testando1();
            List<UserOrders> userOrders1 = compraRepository.findByTypeStock(dto.getId_stock());
            List<UserOrders> userStatus1 = compraRepository.findByStatus();
            List<UserOrders> userteste= compraRepository.fyndteste();
            List<UserOrders> userteste1= compraRepository.findtTeste1();
            if (userOrders.isEmpty()){
                List<UserOrders> userFind = userOrdersRepository.findByCalculo();
                System.out.println(dto.getStatus());
                if(!userteste1.isEmpty()) {
                    System.out.println("compra negativa");
                    for (UserOrders cont:userteste1)  {
                        compraRepository.updateDollarBalance2(cont.getUser());
                        compraRepository.AtuaalizarValue2(cont);
                        //compraRepository.updateStatus(cont);
                        compraRepository.atualizarBalance(cont.getId(),cont.getUser(), cont.getId_stock());
                    }
                }

                if(!userFind.isEmpty() ){
                    System.out.println("venda positiva");
                    for (UserOrders cont: userFind) {//Esse for lista os matches
                        userOrdersRepository.updateDollarBalance(cont.getUser());
                        userOrdersRepository.updateRemainingValue(cont.getId_stock(),cont);
                        userOrdersRepository.atualizarBalance(cont.getUser(), cont.getId_stock());
                        //userOrdersRepository.updateStatus(cont);

                    }
                }

                if(!userteste.isEmpty()){
                    System.out.println("compra positiva");
                    for ( UserOrders cont: userteste ) {
                        compraRepository.updateDollarBalance(cont.getUser());
                        compraRepository.AtuaalizarValue(cont.getId_stock(), cont);
                        compraRepository.updateStatus(cont);
                        compraRepository.atualizarBalance(cont.getId(), cont.getUser(), cont.getId_stock());
                    }
                }
                if (!teste1.isEmpty()){
                    System.out.println("venda negativa");
                    for (UserOrders cont: teste1) {
                        userOrdersRepository.atualizarBalance2(cont.getUser(), cont.getId_stock());
                        userOrdersRepository.updateDollarBalance1(cont, cont.getUser());
                        userOrdersRepository.updateRemainingValue2(cont);
                        userOrdersRepository.updateStatus(cont);
                        System.out.println(cont + "valorrrrrrrr do cont");

                    }
                }

            }

        //if(dto.getType() == 1){

//            if (userOrders1 != null){
//                System.out.println(dto.getStatus());
//                if(!userteste.isEmpty()){
//                    System.out.println("Cheigueieie");
//                    for ( UserOrders cont: userteste ) {
//                        compraRepository.updateDollarBalance(cont.getUser());
//                        compraRepository.AtuaalizarValue(cont);
//                        //compraRepository.updateStatus(cont);
//                        compraRepository.atualizarBalance(cont.getId(), cont.getUser(), cont.getId_stock());
//                    }
//                }
//                if(!userteste1.isEmpty()) {
//                    System.out.println("alouuuuuuuu");
//                    for (UserOrders cont:userteste1)  {
//                        compraRepository.updateDollarBalance2(cont.getUser());
//                        compraRepository.AtuaalizarValue(cont);
//                        //compraRepository.updateStatus(cont);
//                        compraRepository.atualizarBalance(cont.getId(),cont.getUser(), cont.getId_stock());
//                    }
//                }
//            }
        }
        return null;
    }
}
