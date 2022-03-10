package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.TesteDto;
import com.acoes.solinfbreaker.dto.UserOrdersDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.CompraRepository;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.StockService;
import com.acoes.solinfbreaker.service.TesteService;
import com.acoes.solinfbreaker.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private TesteService testeService;
    @Autowired
    private UserStockBalancesRepository usbRepository;

    @GetMapping("/orders")
    public List<UserOrders> listar(){
        return userOrdersRepository.findAll();
    }

    @GetMapping("/uo/{id_user}")
    public ResponseEntity<Page<UserOrders>> getUser(@PathVariable("id_user") Long id_user, @RequestParam int pageSize, @RequestParam int pageNumber) {
        try {
//            userOrderService.dollar_disponivel(id_user);
//            System.out.println(userOrderService.dollar_disponivel(id_user));
            return ResponseEntity.ok().body(userOrderService.getUser(id_user,pageNumber, pageSize));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<UserOrders> salvar(@RequestBody UserOrdersDto dto ,@RequestHeader("Authorization") String token){
        User user = usersRepository.findById(dto.getId_user()).orElseThrow();
        List<UserStockBalances> verificar = usbRepository.verficarStock(dto.getId_user(),dto.getId_stock());
        System.out.println(dto.getType() + "Olhaaaaaaaaaa");
//        System.out.println(dto.getVolume() + " Esse e o volume" + verificar.get(0).getVolume() + " Esse e o volume 2");
        Double dollar = user.getDollar_balance();
        Double mult = dto.getPrice() * dto.getVolume();
        if(dollar >= mult && dto.getType() == 0) {//verifica se o usuario tem dinheiro na carteira pra criar uma ordem de compra
            UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto1(user));
            stockService.teste1(userOrders.getId_stock(), token);
//            userOrderService.vender();
//            testeService.testando();
            testeService.match(dto.tranformaParaObjeto1(user));


            return new ResponseEntity<>(userOrders, HttpStatus.CREATED);
        }
        else if(dto.getType() == 1 &&  !verificar.isEmpty()){
            if (dto.getVolume() <= verificar.get(0).getVolume()) {
                UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto1(user));
                stockService.teste1(userOrders.getId_stock(), token);
//            userOrderService.vender();
                testeService.testando();
                return new ResponseEntity<>(userOrders, HttpStatus.CREATED);
            }
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        return null;
    }

//    @PutMapping("/orders/{id}")
//    public ResponseEntity<UserOrders> update(@PathVariable Long id, @RequestBody TesteDto dto){
//        UserOrders userOrders = userOrdersRepository.save(dto.tranformaParaObjeto2(id));
//        return ResponseEntity.ok(userOrders);
//    }


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



    @PatchMapping("/alterar/{id}")
    public String alterar1(@PathVariable Long id, @RequestBody Map<String, Object> request){
        userOrderService.update(id, request);
        return "Ordem atualizada";
    }


//    @PatchMapping("/orders/{id}")
//    public @ResponseBody void atualizar(@PathVariable Long id, @RequestBody Map<Object, Object> fields){
//        Optional<UserOrders> userOrders = userOrdersRepository.findById(id);
//        fields.forEach((k, v) -> {
//            Field field = ReflectionUtils.findField(UserOrders.class, (String) k);
//            field.setAccessible(true);
//            ReflectionUtils.setField(field, userOrders, v);
//        });
//
//    }



}
