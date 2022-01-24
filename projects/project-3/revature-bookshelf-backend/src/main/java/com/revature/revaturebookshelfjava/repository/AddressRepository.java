package com.revature.revaturebookshelfjava.repository;

import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    // save() is native to JpaRepository
    // Address findAddressByStreetNameAndCityAndStateAndPostalCode(String streetName,String city, String state, int postalCode);
    Optional<Address> findAddressByStreetNameAndCityAndStateAndPostalCode(String streetName, String city, String state, int postalCode);
    Optional<List<Address>> findAddressesByUser(User user);
}
