package com.acoes.solinfbreaker.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table (name="user_stock_balances")
public class UserStockBalances implements Serializable {
    @EmbeddedId
    private UserStockBalance id;

    private String stock_symbol;
    private String stock_name;
    private Long volume;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp created_on;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updated_on;


    public UserStockBalances() {

    }

    public UserStockBalances(UserStockBalance id, String stock_symbol, String stock_name, Long volume){
        this.id = id;
        this.stock_symbol = stock_symbol;
        this.stock_name = stock_name;
        this.volume = volume;

    }

}
