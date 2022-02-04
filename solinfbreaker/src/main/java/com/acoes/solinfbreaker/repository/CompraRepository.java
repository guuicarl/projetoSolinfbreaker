package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CompraRepository extends JpaRepository<UserOrders, Long > {

    @Modifying
    @Query(value = "update user_orders  set remaining_value = (SELECT MAX(b.remaining_value) - MIN(b.volume)AS ID FROM user_orders a, user_orders b  WHERE a.type <> b.type  and a.id_stock = b.id_stock and a.id=?1) where type =  0 AND id=?1", nativeQuery = true)
    int updateRemainingValue(UserOrders id);
//Ele atualiza o raimaning value conforme compras são realizadas


    @Modifying
    @Query(value = "update users set dollar_balance = dollar_balance - ( select uo.remaining_value  * a.price FROM user_orders a, user_orders uo " +
            " inner join users u on uo.id_user = u.id " +
            " where a.status = 1 and a.type = 0 and a.status = uo.status  and a.id_user = ?1 and a.id_user <> uo.id_user and a.id = ?2 fetch first 1 rows only ) where id = ?1", nativeQuery = true)
    int updateDollarBalancePO(User user, UserOrders id);

    @Modifying
    @Query(value = " update users set dollar_balance = dollar_balance  - ( " +
            " select a.remaining_value  * a.price " +
            " fROM user_orders a, user_orders uo " +
            " where  a.id_stock = uo.id_stock and a.type = 0  and uo.id <> a.id and a.id = ?1 and a.id_user = ?2 and a.remaining_value  <> 0 fetch first 1 rows only ) where id = ?2", nativeQuery = true)
    int updateDollarBalanceNE(UserOrders id, User user);


    @Modifying
    @Query(value = "update user_stock_balances set volume = volume + (select a.volume - uo.remaining_value " +
            " AS ID FROM user_orders a, user_orders uo " +
            " inner join users u on uo.id_user = u.id " +
            " inner join user_stock_balances usb on u.id = usb.id_user " +
            " WHERE a.type <> uo.type  and a.id_stock = uo.id_stock and a.id= ?1 fetch first 1 rows only ) where id_user = ?2 and id_stock = ?3", nativeQuery = true)
    int atualizarBalancePO(Long id, User id_user, Long id_stock);

    @Modifying
    @Query(value = "update user_stock_balances set volume = volume -  (select a.volume - uo.remaining_value " +
            " AS ID FROM user_orders a, user_orders uo " +
            " inner join users u on uo.id_user = u.id " +
            " inner join user_stock_balances usb on u.id = usb.id_user " +
            " WHERE a.type <> uo.type  and a.id_stock = uo.id_stock and a.id= ?1 fetch first 1 rows only) where id_user = ?2 and id_stock = ?3", nativeQuery = true)
    int atualizarBalanceNE(Long id, User id_user, Long id_stock);


    @Query(value = " select * from " +
            "                user_orders a, user_orders b where a.type <> b.type and a.remaining_value > b.volume and a.type = 0 and b.type = 1 and a.id_stock = b.id_stock and a.id <> b.id " +
            "                and a.status = 1 and a.remaining_value <> 0 ", nativeQuery = true)
    List<UserOrders> fyndteste();


    @Query(value = "  select * from " +
            "  user_orders a, user_orders b where a.type <> b.type and  a.remaining_value <= b.volume and a.type = 0 and a.status = 1 and a.id_stock = b.id_stock and a.id <> b.id order by a.created_on asc ", nativeQuery = true)
    List<UserOrders> findtTeste1();

    @Modifying
    @Query(value = " update user_orders  set remaining_value = ( select a.remaining_value  - uo.volume  from " +
            " user_orders a, user_orders uo inner join users u on uo.id_user = u.id " +
            " where a.type = 0 and a.id_stock = uo.id_stock  and uo.id = ?1 and uo.id_user = ?2 order by a.created_on asc fetch first 1 rows only) where id=?1 and type = 0", nativeQuery = true)
    int RemainigPO(UserOrders id, User user );

    @Modifying
    @Query(value = " update user_orders  set remaining_value = 0 where  type = 0 and id = ?1", nativeQuery = true)
    int RemainingNE(UserOrders id_order);

}