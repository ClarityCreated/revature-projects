package com.revature.revaturebookshelfjava.repository;

import com.revature.revaturebookshelfjava.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Authority,Integer> {
    Optional<Authority> findByAuthority(String authority);
}
