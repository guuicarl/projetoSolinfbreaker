package com.acoes.solinfbreaker.model;

import java.io.Serializable;
import java.util.Objects;

public class UserStockBalance implements Serializable {

        private Long id;
        private User user;

        public UserStockBalance(Long id, User user){
            this.id = id;
            this.user = user;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStockBalance that = (UserStockBalance) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}

