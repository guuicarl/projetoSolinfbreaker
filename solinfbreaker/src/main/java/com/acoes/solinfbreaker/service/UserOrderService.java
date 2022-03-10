package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.dto.TesteDto;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.CompraRepository;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserOrderService {
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
    public UserOrders vender() {
            List<UserOrders> teste1 = userOrdersRepository.testando1();
            List<UserOrders> userteste= compraRepository.fyndteste();
            List<UserOrders> userteste1= compraRepository.findtTeste1();
            List<UserOrders> userFind = userOrdersRepository.findByCalculo();

                if(!userFind.isEmpty() ){
                    System.out.println("venda positiva");
                    for (UserOrders cont: userFind) {
                        userOrdersRepository.updateDollarBalance(cont.getUser());
                        userOrdersRepository.updateRemainingValue(cont);
//                        userOrdersRepository.atualizarBalance(cont.getUser(), cont.getId_stock());
                    }
                }
                if(!userteste1.isEmpty()) {
                    System.out.println("compra negativa");
                    for (UserOrders cont:userteste1)  {
                        compraRepository.updateDollarBalanceNE(cont, cont.getUser());
//                        compraRepository.teste1(cont.getUser(), cont.getId_stock(), cont.getStock_symbol(), cont.getStock_name());
                        compraRepository.atualizarBalanceNE(cont.getId(),cont.getUser(), cont.getId_stock());
                        compraRepository.RemainingNE(cont);
                    }
                }
                if(!userteste.isEmpty()){
                    System.out.println("compra positiva");
                    for ( UserOrders cont: userteste ) {
//                        compraRepository.teste1(cont.getUser(), cont.getId_stock(), cont.getStock_symbol(), cont.getStock_name());
                        compraRepository.updateDollarBalancePO(cont.getUser(), cont);
                        compraRepository.RemainigPO(cont, cont.getUser());
//                        compraRepository.teste1(cont.getUser(), cont.getId_stock(), cont.getStock_symbol(), cont.getStock_name());
                        compraRepository.atualizarBalancePO(cont.getId(), cont.getUser(), cont.getId_stock());
                    }
                    //userOrdersRepository.updateStatus();
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
        return null;
    }
    public Page<UserOrders> getUser(Long id_user, int pageNumber, int pageSize) throws Exception {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return userOrdersRepository.listOrders(id_user, page);
    }

    public void update(Long id, Map<String, Object> request){
        UserOrders uo  = userOrdersRepository.findById(id).orElseThrow(() -> {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado");
        });
        request.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(UserOrders.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, uo, v);
        });
        userOrdersRepository.save(uo);
    }
//    public Double dollar_disponivel(Long id_user){
//        List<UserOrders> userOrders = userOrdersRepository.listOrders(id_user);
//        for(int i =0; i < userOrders.size(); i++){
//            if(userOrders.get(i).getType() == 1) {
//                return userOrders.get(i).getUser().getDollar_balance() - userOrders.get(i + 1).getUser().getDollar_balance();
//            }
//        }
//        return userOrders.get(0).getUser().getDollar_balance();
//    }


}
