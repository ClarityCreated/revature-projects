package com.revature.revaturebookshelfjava.repository;

import com.revature.revaturebookshelfjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    // TODO: Optional<T> usage
    Optional<User> findByEmail(String username);

    // save() is native to JpaRespository<T,U>
}

