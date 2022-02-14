package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class StockDto {
    private Long id;
    private String stock_symbol;
    private String stock_name;
    private Double ask_min;
    private Double ask_max;
    private Double bid_min;
    private Double bid_max;

    public StockDto(Long id, String stock_symbol, String stock_name, Double ask_min, Double ask_max, Double bid_min, Double bid_max) {
        this.id = id;
        this.stock_symbol = stock_symbol;
        this.stock_name = stock_name;
        this.ask_min = ask_min;
        this.ask_max = ask_max;
        this.bid_min = bid_min;
        this.bid_max = bid_max;
    }
    public StockDto tranformaParaObjeto1(){
        return new StockDto (id, stock_symbol, stock_name, ask_min, ask_max, bid_min, bid_max);
    }
}
