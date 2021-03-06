package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.dto.UserStockDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.CompraRepository;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class TesteService {
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserStockBalancesRepository repository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private WebClient webClient;
    @Autowired
    private StockService stockService;
    public UserOrders testando() {
        List<UserOrders> teste1 = userOrdersRepository.testando1();
        List<UserOrders> userteste= compraRepository.fyndteste();
        List<UserOrders> userteste1= compraRepository.findtTeste1();
        List<UserOrders> userFind = userOrdersRepository.findByCalculo();
        System.out.println(userFind.hashCode());
        if(!userFind.isEmpty() ){
            System.out.println("venda positiva/compra negativa");
            for (int a = 0; a < userFind.size(); a = a+1) {
                if (userFind.get(a).getRemaining_value() >= userteste1.get(a).getRemaining_value()){
                //Atualiza dollar balance do venda positiva e compra negativa
                userFind.get(a).getUser().setDollar_balance(userFind.get(a).getUser().getDollar_balance() + (userFind.get(a).getPrice() * userteste1.get(a).getRemaining_value()));
                userteste1.get(a).getUser().setDollar_balance(userteste1.get(a).getUser().getDollar_balance() - userFind.get(a).getPrice() * userteste1.get(a).getRemaining_value());
                //Atualiza a carteira do usuario do venda positiva e compra negativa
                List<UserStockBalances> userStockBalances1 = repository.atualizarBalance(userFind.get(a).getUser().getId(), userFind.get(a).getId_stock());
                userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() - userteste1.get(a).getRemaining_value());
                List<UserStockBalances> userStockBalances = repository.atualizarBalance(userteste1.get(a).getUser().getId(), userteste1.get(a).getId_stock());
                if(userStockBalances.isEmpty()){
                    compraRepository.teste1(userteste1.get(a).getUser(), userteste1.get(a).getId_stock(), userteste1.get(a).getStock_symbol(), userteste1.get(a).getStock_name(), userteste1.get(a).getVolume());
                }else{
                    userStockBalances.get(0).setVolume(userStockBalances.get(0).getVolume() + userteste1.get(a).getRemaining_value());
                }
                //Atualiza o remaining value do venda positiva e compra negativa
                userFind.get(a).setRemaining_value(userFind.get(a).getRemaining_value() - userteste1.get(a).getRemaining_value());
                userteste1.get(a).setRemaining_value(Long.valueOf(0));
                }
            }
        }
        if(!userteste.isEmpty()){
            System.out.println("compra positiva");
            for ( int a = 0; a < userteste.size(); a++ ) {
                if (userteste.get(a).getRemaining_value() >= teste1.get(a).getRemaining_value()) {
                    //Atualiza dollar balance do venda negativa e compra positiva
                    userteste.get(a).getUser().setDollar_balance(userteste.get(a).getUser().getDollar_balance() - (teste1.get(a).getPrice() * teste1.get(a).getVolume()));
                    teste1.get(a).getUser().setDollar_balance(teste1.get(a).getUser().getDollar_balance() + teste1.get(a).getPrice() * teste1.get(a).getVolume());
                    //Atualiza carteira do venda negativa e compra positiva
                    List<UserStockBalances> userStockBalances = repository.atualizarBalance(teste1.get(a).getUser().getId(), teste1.get(a).getId_stock());
                    userStockBalances.get(0).setVolume(userStockBalances.get(0).getVolume() - teste1.get(a).getVolume());
                    List<UserStockBalances> userStockBalances1 = repository.atualizarBalance(userteste.get(a).getUser().getId(), userteste.get(a).getId_stock());
                    if (userStockBalances1.isEmpty()) {
                        compraRepository.teste1(userteste.get(a).getUser(), userteste.get(a).getId_stock(), userteste.get(a).getStock_symbol(), userteste.get(a).getStock_name(), teste1.get(a).getVolume());
                        System.out.println("to aqui");
                    } else {
                        userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() + teste1.get(a).getVolume());
                    }
                    //Atualiza o remanining value do venda negativa e compra positiva
                    userteste.get(a).setRemaining_value(userteste.get(a).getRemaining_value() - teste1.get(a).getRemaining_value());
                    teste1.get(a).setRemaining_value(Long.valueOf(0));
                }

            }
        }
        userOrdersRepository.updateStatus2();
        return null;
    }
//    public List<UserOrders> getUser(Long id_user) throws Exception {
//        List<UserOrders> userOrders = userOrdersRepository.listOrders(id_user);
//        return userOrders;
//    }

    public UserOrders match(UserOrders dto){
        List<UserOrders> userOrders = userOrdersRepository.findAll();

        for (UserOrders cont: userOrders) {
            if (dto.getId_stock() == cont.getId_stock() && dto.getType() != cont.getType()){
                if (dto.getRemaining_value() >= cont.getRemaining_value()) {
                    System.out.println("stock da nova ordem: " + dto.getId_stock() + " Voluma da nova ordem: " + dto.getVolume());
                    System.out.println("Deu match: " + cont.getId_stock() + " Volume do match: " + cont.getVolume() + " Id do match: " + cont.getId());
                    dto.setRemaining_value(remaining_value(dto.getRemaining_value(), cont.getRemaining_value()));
                    cont.setRemaining_value(remaining_value(cont.getRemaining_value(), dto.getRemaining_value()));
                    System.out.println("Valor do calculo: " + dto.getRemaining_value());
                }
            }
        }
        userOrdersRepository.updateStatus2();
        return null;
    }

    public Long remaining_value(Long a, Long b){
        if (a >= b){
            return a-b;
        } else {
            System.out.println("entrei aquiiiii");
            return Long.valueOf(0);
        }
    }

    public Double dollar_balance(Double dollar, Double remaining, Double pricev, Long tipo, Long volumec){
        if (tipo == 1){
            return dollar + remaining*pricev;
        } else {
            return  dollar - remaining*pricev;
        }
    }

    public Double dollar_disponivel(Long volume, Double price, Long tipo, Double dollar){
        if (tipo == 0){
            return dollar - (volume * price);
        } else {
            return null;
        }
    }

    public Double fecharOrdemC(Long volume, Double price, Double dollar, Long tipo){
        if (tipo == 0){
            return dollar + volume * price;
        } else {
            return null;
        }
    }

    public Long fecharOrdemV(Long tipo, Long volume, Long remaining){
        if(tipo == 1){
            return volume - remaining;
        }
        return null;
    }

    public Long volumeDisponivel(Long volume, Long usb){
        return  usb - volume;
    }

    public Long atualizarUsbVenda (Long volume, Long remaining, Long tipo, Long usbVol, User user, Long id_stock, String name, String symbol, UserStockDto dto){
        List<UserStockBalances> userStockBalances1 = repository.atualizarBalanceTeste(user, id_stock);
        if(tipo == 1 && remaining != 0){
            return usbVol - (volume - remaining);
        } else if(tipo == 1 && remaining == 0) {
            return usbVol - volume;
        }
        if(userStockBalances1.isEmpty()){
//            dto.setId_user(user);
//            dto.setId_stock(id_stock);
//            dto.setVolume(volume);
//            dto.setStock_name(name);
//            dto.setStock_symbol(symbol);
            repository.save(new UserStockBalances(new UserStockBalance(user, id_stock), symbol, name, volume));
            return null;
        } else {
            return usbVol = dto.getVolume() - volume;
        }
    }

}
