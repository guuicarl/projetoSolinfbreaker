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
List<UserOrders> findByTypeStock(Long id_stock);



//@Query(value = "SELECT MAX(a.volume) - MIN(b.volume), a.id  AS ID FROM user_orders a, user_orders b  WHERE a.type = 1 AND a.volume <> b.volume and a.id_stock = ?1 group by a.id", nativeQuery = true)
@Query(value = "SELECT * FROM user_orders a, user_orders b  where a.type <> b.type  and a.id_stock = b.id_stock and a.status = 1 ", nativeQuery = true)
    List<UserOrders> findByCalculo();

@Modifying
    //@Query(value = "update user_orders  set remaining_value = (SELECT MAX(a.volume) - MIN(b.volume) AS ID FROM user_orders a, user_orders b  WHERE a.type = 1  and a.id_stock = b.id_stock) where type =  1 AND id=?1", nativeQuery = true)
    @Query(value = "update user_orders  set remaining_value = (SELECT MAX(a.volume) - MIN(b.volume)AS ID FROM user_orders a, user_orders b  WHERE a.type <> b.type  and a.id_stock = b.id_stock and a.id=?1) where type =  1 AND id=?1", nativeQuery = true)

    int updateRemainingValue(UserOrders id);


    @Modifying
    @Query(value = "UPDATE user_orders SET status = 2 WHERE id = ?1 and remaining_value = 0 ", nativeQuery = true)
    int updateStatus(UserOrders id);

    @Modifying
    @Query(value = "UPDATE user_orders SET status = 3 WHERE id = ?1 and remaining_value = 0 ", nativeQuery = true)
    int updateStatus2(UserOrders id);

    @Modifying
    @Query(value = "update users set dollar_balance = (SELECT (uo.price * uo.volume) + dollar_balance FROM user_orders uo inner join users u on uo.id_user = u.id  where uo.status = 2 and uo.id_user = ?1) where id = ?1", nativeQuery = true)
    int updateDollarBalance (User user);
//@Modifying
//@Query(value = "update users  set dollar_balance  = (select SUM(case when u.id = ?1 and status = 2 then ((uo.price * uo.volume) + dollar_balance)else 0end)AS total FROM user_orders uo inner join users u on uo.id_user = u.id) where id = ?1", nativeQuery = true)
//int updateDollarBalance (UserOrders id);

    @Query(value = "SELECT * from user_orders where status =1 and type = 1 ", nativeQuery = true)
    List<UserOrders> findByStatus();




}
