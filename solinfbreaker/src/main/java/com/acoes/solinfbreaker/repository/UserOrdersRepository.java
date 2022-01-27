package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserOrdersRepository extends JpaRepository<UserOrders, Long> {
//@Query(value = "SELECT * FROM user_orders WHERE type = 1 and id_stock = ?1 and status = 1", nativeQuery = true)
@Query(value = "SELECT * FROM user_orders WHERE type = 1 and id_stock = ?1 and status = 1", nativeQuery = true)
List<UserOrders> findByTypeStock(Long id_stock);//procurar ordens de venda abertas



//@Query(value = "SELECT MAX(a.volume) - MIN(b.volume), a.id  AS ID FROM user_orders a, user_orders b  WHERE a.type = 1 AND a.volume <> b.volume and a.id_stock = ?1 group by a.id", nativeQuery = true)
@Query(value = "SELECT * FROM user_orders a, user_orders b  where a.type <> b.type  and a.id_stock = b.id_stock and a.status = b.status", nativeQuery = true)
    List<UserOrders> findByCalculo();//Pegando matches

@Modifying
    //@Query(value = "update user_orders  set remaining_value = (SELECT MAX(a.volume) - MIN(b.volume) AS ID FROM user_orders a, user_orders b  WHERE a.type = 1  and a.id_stock = b.id_stock) where type =  1 AND id=?1", nativeQuery = true)
    @Query(value = "update user_orders  set remaining_value = (SELECT MAX(a.remaining_value) - MIN(b.volume)AS ID FROM user_orders a, user_orders b  WHERE a.type <> b.type  and a.id_stock = b.id_stock and a.id=?1) where type =  1 AND id=?1", nativeQuery = true)

    int updateRemainingValue(UserOrders id);//Ele atualiza remaining value quando há match


    @Modifying
    @Query(value = "UPDATE user_orders SET status = 1 WHERE id = ?1 and remaining_value <> volume", nativeQuery = true)
    int updateStatus(UserOrders id);

    @Modifying
    @Query(value = "UPDATE user_orders SET status = 3 WHERE id = ?1 and remaining_value = 0 ", nativeQuery = true)
    int updateStatus2(UserOrders id);

    @Modifying
//    @Query(value = "update users set dollar_balance = (SELECT (MAX(b.volume) - MIN(a.remaining_value)) * a.price + u.dollar_balance  AS ID " +
//            "FROM user_orders a, user_orders b, user_orders as uo " +
//            "inner join users u on uo.id_user = u.id " +
//            "where a.status = 1 and a.id_user = b.id_user and a.id_stock = b.id_stock and a.type = 1 " +
//            "GROUP BY a.price, u.dollar_balance) where id = ?1", nativeQuery = true)

    @Query(value = "update users set dollar_balance = (select a.volume  * uo.price + u.dollar_balance " +
            "FROM user_orders a, user_orders uo " +
            "inner join users u on uo.id_user = u.id " +
            "where a.status = 1  and a.id_stock = uo.id_stock and a.type = 0  and a.status = uo.status and uo.id <> a.id and u.id = ?1) where id = ?1", nativeQuery = true)
    int updateDollarBalance (User user);

//    @Query(value = "with t as (" +
//            "SELECT ((MAX(b.volume) - MIN(a.remaining_value)) * a.price + u.dollar_balance) as ss " +
//            "FROM user_orders a, user_orders b, user_orders as uo " +
//            "inner join users u on uo.id_user = u.id " +
//            "where a.status = 1 and a.id_user = b.id_user and a.id_stock = b.id_stock and a.type = 1  group BY a.price, u.dollar_balance " +
//            " ) update users " +
//            "set dollar_balance = t.ss " +
//            "from t " +
//            "where id = ?1", nativeQuery = true)
//    int updateDollarBalance (User user);

    @Modifying
    @Query(value = "update users set dollar_balance = (select a.volume  * uo.price + u.dollar_balance " +
            "FROM user_orders a, user_orders uo " +
            "inner join users u on uo.id_user = u.id " +
            "where a.status = 1  and a.id_stock = uo.id_stock and a.type = 0  and a.volume = uo.volume and a.status = uo.status and uo.id <> a.id ) where id = ?1", nativeQuery = true)
    int updateDollarBalance1 (User user);
//@Modifying
//@Query(value = "update users  set dollar_balance  = (select SUM(case when u.id = ?1 and status = 2 then ((uo.price * uo.volume) + dollar_balance)else 0end)AS total FROM user_orders uo inner join users u on uo.id_user = u.id) where id = ?1", nativeQuery = true)
//int updateDollarBalance (UserOrders id);

    @Query(value = "SELECT * from user_orders where status =1 and type = 1 ", nativeQuery = true)
    List<UserOrders> findByStatus();

    @Modifying
    @Query(value = "update user_stock_balances set volume = (SELECT MAX(usb.volume) - MIN(uo.remaining_value)AS ID FROM user_orders uo inner join users u on uo.id_user = u.id inner join user_stock_balances usb on u.id = usb.id_user where uo.id_user = 1 and uo.id_stock = 5) where id_user =1 and id_stock=5",nativeQuery = true)
    int selectLinha();

    @Query(value = "select * from user_stock_balances usb inner join users u on usb.id_user = u.id inner join user_orders uo  on u.id = uo.id_user where uo.id_stock = usb.id_stock ", nativeQuery = true)
    List<UserOrders> findStockExists();

    @Modifying
    @Query(value = "insert into user_stock_balances (id_user, id_stock,  stock_symbol, stock_name, volume)(select id_user, id_stock, stock_symbol, stock_name, volume from user_orders uo)",nativeQuery = true)
    @Transactional
    void inserirStock();

    @Modifying
    @Query(value = "update user_stock_balances set volume = (select MIN (usb.volume - (usb.volume - uo.remaining_value)) AS ID FROM user_orders uo inner join users u on uo.id_user = u.id inner join user_stock_balances usb on u.id = usb.id_user where uo.id_user = ?1 and uo.id_stock = ?2) where id_user = ?1 and id_stock = ?2",nativeQuery = true)
    int atualizarBalance(User id_user, Long id_stock);


//    @Modifying
//    @Query(value = "DELETE FROM user_stock_balances WHERE volume = 5")
//    default void deleteLinha() {
//
//    }
@Query(value = "SELECT MAX(a.remaining_value) - MIN(b.volume) AS ID FROM user_orders a, user_orders b where a.status = 1 and a.id_user = b.id_user   and a.id_stock = b.id_stock",nativeQuery = true)
int selecionarSub();

    @Query(value = "SELECT * FROM user_orders a, user_orders b  where a.type =1  and a.id_stock = b.id_stock  and a.volume > b.volume  ", nativeQuery = true)
    List<UserOrders> novoTeste();




}
