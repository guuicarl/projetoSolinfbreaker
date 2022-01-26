//package com.acoes.solinfbreaker.controller;
//
//import com.acoes.solinfbreaker.dto.UserOrdersDto;
//import com.acoes.solinfbreaker.model.UserOrders;
//import com.acoes.solinfbreaker.repository.UserOrdersRepository;
//import com.acoes.solinfbreaker.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class VendaController {
//    @Autowired
//    private UserOrdersRepository userOrdersRepository;
//    @Autowired
//    private UsersRepository usersRepository;
//    @PostMapping("/venda")
//    public UserOrders comprar(@RequestBody UserOrdersDto dto) throws SQLException {
//
//        if(dto.getType() == 0){
//            List<UserOrders> userOrders =userOrdersRepository.findByTypeStock(dto.getId_stock());
//            List<UserOrders> userStatus = userOrdersRepository.findByStatus();
//            if (userOrders != null){
//                List<UserOrders> userFind = userOrdersRepository.findByCalculo();
////                List<UserOrders> userStatus = userOrdersRepository.findByStatus();
//                System.out.println(dto.getStatus());
//                if(!userFind.isEmpty() ){
//                    System.out.println("cheguei");
//
//                    for (UserOrders cont: userFind) {
//                        userOrdersRepository.updateRemainingValue(cont);
//                        userOrdersRepository.updateStatus(cont);
//                    }
//                }
//                if (!userStatus.isEmpty()){
//                    System.out.println("Cheguei");
//                    for (UserOrders cont: userStatus) {
//                        System.out.println("novo");
//                        userOrdersRepository.updateDollarBalance(cont.getUser());
//                        userOrdersRepository.updateStatus2(cont);
//                    }
//                }
//
//            }
//        }
//        return null;
//    }
//}
