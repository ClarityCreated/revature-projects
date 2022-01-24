package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.User;

import java.util.List;

public interface AddressService {
    void registerAddress(Address address, User user) throws Exception;
    List<Address> getAddressByUser(User user);
}
