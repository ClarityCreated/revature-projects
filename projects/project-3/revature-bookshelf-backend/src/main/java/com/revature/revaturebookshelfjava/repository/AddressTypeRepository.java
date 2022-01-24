package com.revature.revaturebookshelfjava.repository;

import com.revature.revaturebookshelfjava.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressTypeRepository extends JpaRepository<AddressType, Integer> {

    Optional<AddressType> findByType(String type);
//    AddressType findByType(String type);
}
