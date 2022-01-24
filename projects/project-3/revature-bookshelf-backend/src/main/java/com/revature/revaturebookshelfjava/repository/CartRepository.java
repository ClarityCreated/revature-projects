package com.revature.revaturebookshelfjava.repository;


import com.revature.revaturebookshelfjava.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserId(int userId);

//    @Query("from Cart c join c.user u where u.username=: username")
//    Optional<Cart> findByUsername(String username);
}