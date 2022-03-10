//package com.acoes.solinfbreaker.service;
//
//import com.acoes.solinfbreaker.dto.GraficoDto;
//import com.acoes.solinfbreaker.dto.StockDto;
//import com.acoes.solinfbreaker.model.UserOrders;
//import com.acoes.solinfbreaker.repository.UserOrdersRepository;
//import net.minidev.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.Timer;
//import java.util.TimerTask;
//@Component
//@EnableScheduling
//@Service
//public class GraficoService {
//    @Autowired
//    private UserOrdersRepository userOrdersRepository;
//    @Autowired
//    private WebClient webClient;
//    private final long SEGUNDO = 1000;
//    private final long MINUTO = SEGUNDO *60;
//    private final long HORA = MINUTO * 60;
//
////    @Scheduled(fixedDelay = MINUTO)
////    public void teste(){
////        System.out.println("aeeeeeeeeeeeeeeeeeeeeeeeeeee");
////    }
//
////    @Scheduled(fixedDelay = MINUTO)
//    public GraficoDto atualizarGrafico(Long id, @RequestHeader("Authorization") String token) {
//        JSONObject json = new JSONObject();;
//        json.put("id_stock", id);
//        json.put("aberto", userOrdersRepository.getOpen(id));
//        json.put("fechado", userOrdersRepository.getClose(id));
//        json.put("high", userOrdersRepository.getMax(id));
//        json.put("low", userOrdersRepository.getLow(id));
//        Mono<GraficoDto> monoStock =
//                this.webClient
//                        .post()
//                        .uri("/historico")
//                        .header(HttpHeaders.AUTHORIZATION, token)
//                        .body(BodyInserters.fromValue(json))
//                        .retrieve()
//                        .bodyToMono(GraficoDto.class);
//        GraficoDto graficoDto = monoStock.block();
//        return graficoDto;
//    }
//}
