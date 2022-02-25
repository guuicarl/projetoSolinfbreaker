package com.acoes.solinfbreaker.controller;


import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.model.Stocks;
import com.acoes.solinfbreaker.repository.StocksRepository;
import com.acoes.solinfbreaker.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
public class StocksController {
    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private StocksService service;

    @GetMapping("/stocks/{id}")
    public Optional<Stocks> obterStock(@PathVariable(value = "id")Long id) throws Exception {
        Thread.sleep(3000);
        return stocksRepository.findById(id);
    }

    @GetMapping("/{stock_name}")
    public ResponseEntity<List<Stocks>> getStocks(@PathVariable("stock_name") String stock_name) {
        try {
            return ResponseEntity.ok().body(service.getStock(stock_name));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }
    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping(value = "/temporeal")
    public SseEmitter temporeal(HttpServletResponse response){
        response.setHeader("Cache_Control", "no-store");
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            emitters.add(sseEmitter);
        } catch (Exception e){
            e.printStackTrace();
        }
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        return sseEmitter;
    }

    @GetMapping("/updated")
    public List<Stocks> listar10(){
        return stocksRepository.findStocks();
    }


    @GetMapping("/stocks")
    public List<Stocks> listar(){
        return stocksRepository.findAll();
    }

    @PostMapping("/teste")
    public ResponseEntity<?> teste(@RequestBody StockDto stockDto){
        Stocks stock = stocksRepository.findById(stockDto.getId()).orElseThrow();
        if(stockDto.getAsk_max() != null) {
            stock.setAsk_max(stockDto.getAsk_max());
            stock.setAsk_min(stockDto.getAsk_min());
        }
        if (stockDto.getBid_min() != null) {
            stock.setBid_max(stockDto.getBid_max());
            stock.setBid_min(stockDto.getBid_min());
        }
         stock = stocksRepository.save(stock);
        publicar();

//        for(SseEmitter emitter : emitters) {
//            try {
//                emitter.send(SseEmitter.event().name("latestupdate").data(stocksRepository.findAll()));
//            } catch (IOException e){
//                emitters.remove(emitter);
//            }
//        }

        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    public void publicar(){
        for(SseEmitter emitter : emitters) {
            try {
                emitter.send(stocksRepository.findStocks());
            } catch (IOException e){
                emitters.remove(emitter);
            }
        }
    }

}
