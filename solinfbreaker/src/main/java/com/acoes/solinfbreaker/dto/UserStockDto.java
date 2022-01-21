package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStockDto {
    private Long id_user;
    private Long id_stock;
    private String stock_symbol;
    private String stock_name;
    private Long volume;

    public UserStockBalances tranformaParaObjeto(User user){
        return new UserStockBalances(new UserStockBalance(user, id_stock), stock_symbol, stock_name, volume);
    }

}
