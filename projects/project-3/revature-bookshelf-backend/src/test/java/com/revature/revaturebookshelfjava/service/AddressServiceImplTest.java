package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.AddressType;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.repository.AddressRepository;
import com.revature.revaturebookshelfjava.repository.AddressTypeRepository;
import com.revature.revaturebookshelfjava.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class AddressServiceImplTest {

    private static AddressServiceImpl addressService;
    private static AddressRepository addressRepositoryMock;
    private static AddressTypeRepository addressTypeRepositoryMock;
    private static UserRepository userRepositoryMock;

    @BeforeEach
    public void init() {
        addressTypeRepositoryMock = Mockito.mock(AddressTypeRepository.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        addressRepositoryMock = Mockito.mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepositoryMock,addressTypeRepositoryMock,userRepositoryMock);
    }

    @BeforeEach
    public void recordMocks() {
        Mockito.when(addressRepositoryMock.findAddressByStreetNameAndCityAndStateAndPostalCode("1 One St.","City","State",1))
                .thenReturn(Optional.empty());
        Mockito.when(addressTypeRepositoryMock.findByType("invalid")).thenReturn(Optional.empty());
        Mockito.when(addressRepositoryMock.findAddressByStreetNameAndCityAndStateAndPostalCode("2 Two St.","City","State",1))
                .thenReturn(Optional.of(new Address("2 Two St.","City","State",1,List.of(new AddressType("valid")))));
        Mockito.when(addressTypeRepositoryMock.findByType("valid")).thenReturn(Optional.of(new AddressType("valid")));
        Mockito.when(addressTypeRepositoryMock.findByType("other valid")).thenReturn(Optional.of(new AddressType("other valid")));
        Mockito.when(addressRepositoryMock.findAddressesByUser(new User()))
                .thenReturn(Optional.of(List.of(new Address("2 Two St.","City","State",1,List.of(new AddressType("valid"))))));
        Mockito.when(userRepositoryMock.save(new User(List.of(new Address("1 One St.","City","State",1,List.of(new AddressType("valid")))))))
                .thenReturn(new User(List.of(new Address("1 One St.","City","State",1,List.of(new AddressType("valid"))))));
        Mockito.when(userRepositoryMock.save(new User(List.of(new Address("1 One St.","City","State",1,List.of(new AddressType("valid"), new AddressType("other valid")))))))
                .thenReturn(new User(List.of(new Address("1 One St.","City","State",1,List.of(new AddressType("valid"), new AddressType("other valid"))))));
    }

    @Test
    @DisplayName("Address Not Found in Database and Invalid Type Registration Test")
    public void registerAddressNoExistWithInvalidTypeTest() {
        Address address = new Address();
        address.setStreetName("1 One St.");
        address.setCity("City");
        address.setState("State");
        address.setPostalCode(1);
        address.setTypes(List.of(new AddressType("invalid")));
        Exception exception = assertThrows(Exception.class,()->addressService.registerAddress(address,new User()));
        String expected = "Invalid address type";
        Assertions.assertEquals(expected,exception.getMessage());
    }

    @Test
    @DisplayName("Address Found in Database and Invalid Type Registration Test")
    public void registerAddressExistWithInvalidTypeTest() {
        Address address = new Address();
        address.setStreetName("2 Two St.");
        address.setCity("City");
        address.setState("State");
        address.setPostalCode(1);
        address.setTypes(List.of(new AddressType("invalid")));
        Exception exception = assertThrows(Exception.class,()->addressService.registerAddress(address,new User()));
        String expected = "Invalid address type";
        Assertions.assertEquals(expected,exception.getMessage());
    }

    @Test
    @DisplayName("Duplicate Address Registration Test")
    public void registerAddressDuplicateTest() {
        Address address = new Address();
        address.setStreetName("2 Two St.");
        address.setCity("City");
        address.setState("State");
        address.setPostalCode(1);
        address.setTypes(List.of(new AddressType("valid")));
        Exception exception = assertThrows(Exception.class,()->addressService.registerAddress(address,new User()));
        String expected = "Address already exists";
        Assertions.assertEquals(expected,exception.getMessage());
    }

    @Disabled
    @Test
    @DisplayName("Successful Address Not in Database Registration Test")
    public void registerAddressNoExistSuccessful() {
        Address address = new Address();
        address.setStreetName("1 One St.");
        address.setCity("City");
        address.setState("State");
        address.setPostalCode(1);
        address.setTypes(List.of(new AddressType("valid")));
        User actualUser = new User();
        User expectedUser = new User();
        expectedUser.setAddresses(List.of(address));
        try {
            addressService.registerAddress(address,actualUser);
            Assertions.assertEquals(expectedUser,actualUser);
        } catch(Exception e) {
            fail("Exception was unexpectedly thrown: " + e.getMessage());
        }
    }

    @Disabled
    @Test
    @DisplayName("Successful Address Found in Database Registration Test")
    public void registerAddressExistSuccessful() {
        Address address = new Address();
        address.setStreetName("3 Three St.");
        address.setCity("City");
        address.setState("State");
        address.setPostalCode(1);
        address.setTypes(List.of(new AddressType("other valid")));
        User actualUser = new User();
        User expectedUser = new User();
        expectedUser.setAddresses(List.of(new Address("3 Three St.","City","State",1,List.of(new AddressType("valid"),new AddressType("other valid")))));
        try {
            addressService.registerAddress(address,actualUser);
            Assertions.assertEquals(expectedUser, actualUser);
        } catch (Exception e){
            fail("Exception was unexpectedly thrown: "+ e.getMessage());
        }
    }
}
