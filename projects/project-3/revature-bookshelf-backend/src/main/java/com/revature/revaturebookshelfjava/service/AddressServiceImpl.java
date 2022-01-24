package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.AddressType;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.repository.AddressRepository;
import com.revature.revaturebookshelfjava.repository.AddressTypeRepository;
import com.revature.revaturebookshelfjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressTypeRepository addressTypeRepository;

    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressTypeRepository addressTypeRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.addressTypeRepository = addressTypeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void registerAddress(Address address, User user) throws Exception {

        Optional<Address> optionalAddress = addressRepository.findAddressByStreetNameAndCityAndStateAndPostalCode(address.getStreetName(), address.getCity(), address.getState(), address.getPostalCode());
        if (optionalAddress.isEmpty()) {
            Address newAddress = address;
            List<AddressType> newTypes = new ArrayList<>();
            // Optional<List<AddressType>> allTypes = addressTypeRepository.findAll();
            for (AddressType addType : address.getTypes()) {
                Optional<AddressType> optionAddressType = addressTypeRepository.findByType(addType.getType());
                if (optionAddressType.isEmpty()) {
                    throw new Exception("Invalid address type");
                }
                newTypes.add(optionAddressType.get());
            }
            newAddress.setTypes(newTypes);
            List<Address> addyList = new ArrayList<>(user.getAddresses());
            addyList.add(newAddress);
            user.setAddresses(addyList);
            userRepository.save(user);
            //addressRepository.save(newAddress);
        } else {
            if (optionalAddress.get().getTypes().equals(address.getTypes())) {
                throw new Exception("Address already exists");
            } else {
                Address newAddress = optionalAddress.get();
                List<AddressType> newTypes = new ArrayList<>();
                // Optional<List<AddressType>> allTypes = addressTypeRepository.findAll();
                for (AddressType addType : address.getTypes()) {
                    Optional<AddressType> optionAddressType = addressTypeRepository.findByType(addType.getType());
                    if (optionAddressType.isEmpty()) {
                        throw new Exception("Invalid address type");
                    }
                    newTypes.add(optionAddressType.get());
                }
                newAddress.setTypes(newTypes);
                //List<Address> addyList = new ArrayList<>(user.getAddresses());
                Optional<List<Address>> ownedAddresses = addressRepository.findAddressesByUser(user);//
                List<Address> addyList = new ArrayList<>();
                //addyList.add(newAddress);
                //.addAll(ownedAddresses.get());

//                List<Address> ownedAddressesList = ownedAddresses.get();
//                for (Address a: ownedAddressesList) {
//                    if (newAddress.equals(a)) {
//                        List<AddressType> templist = new ArrayList<>();
//                        templist.addAll(a.getTypes());
//                        templist.addAll(newTypes);
//                        a.setTypes(templist);
//                    }
//                }
//                //addyList.add(newAddress);
//                //user.setAddresses(addyList);
//                user.setAddresses(ownedAddressesList);

                List<Address> ownedAddressesList = ownedAddresses.get();
                for (Address a: ownedAddressesList) {
                    if (newAddress.equals(a)) {
                        Set<AddressType> tempSet = new HashSet<>();
                        tempSet.addAll(a.getTypes());
                        tempSet.addAll(newTypes);
                        a.setTypes(new ArrayList<AddressType>(tempSet));
                    }
                }
//                List<AddressType> tempList = new ArrayList<>();
//                tempList.addAll(tempSet);
                user.setAddresses(ownedAddressesList);

                // SAVE
                userRepository.save(user);
            }
        }
    }

    @Override
    public List<Address> getAddressByUser(User user) {
        Optional<List<Address>> optionalAddressList = addressRepository.findAddressesByUser(user);
        if (optionalAddressList.isEmpty()){
            // return empty list
            return new ArrayList<Address>();
        }
        return  optionalAddressList.get();
    }
}
