package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrdersDto {
    private Long id;
    private Long id_user;
    private Long id_stock;
    private String stock_symbol;
    private String stock_name;
    private Long volume;
    private Double price;
    private Integer type;
    private Integer status;
    private Long remaining_value;


    public UserOrders tranformaParaObjeto1(User user){
        return new UserOrders (user, id, id_stock, stock_symbol, stock_name, volume, price, type, status,remaining_value);
    }
}
