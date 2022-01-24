package com.revature.revaturebookshelfjava.repository;


import com.revature.revaturebookshelfjava.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

//    @Query("from Order o join o.cart c join c.user u where u.id=:userId")
//    List<Order> findByCartUserId(int userId);
//
//    @Query("from Order o join o.cart c join c.user u where u.username=:username")
//    List<Order> findByCartUserUsername(String username);
}