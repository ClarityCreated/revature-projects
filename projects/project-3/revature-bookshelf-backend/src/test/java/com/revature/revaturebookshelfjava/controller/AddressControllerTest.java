package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.authenicator.extractor.UserDetailsExtractor;
import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.service.AddressService;
import com.revature.revaturebookshelfjava.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

public class AddressControllerTest {

    private static AddressController addressController;
    private static AddressService addressServiceMock;
    private static UserService userServiceMock;
    private static UserDetailsExtractor userDetailsExtractorMock;

    @BeforeEach
    public void init() {
        addressServiceMock = Mockito.mock(AddressService.class);
        userServiceMock = Mockito.mock(UserService.class);
        userDetailsExtractorMock = Mockito.mock(UserDetailsExtractor.class);
        addressController = new AddressController(userServiceMock,addressServiceMock,userDetailsExtractorMock);
    }

    @BeforeEach
    public void recordMock() throws Exception {
        Mockito.when(userDetailsExtractorMock.extractUsername()).thenReturn("username");
        Mockito.when(userServiceMock.getUser("username")).thenReturn(new User());
        Mockito.when(addressServiceMock.getAddressByUser(new User())).thenReturn(List.of(new Address("1 One St.","City","State",1)));
        Mockito.doNothing().when(addressServiceMock).registerAddress(new Address("1 One St.","City","State",1),new User());
    }

    @Test
    @DisplayName("Get All Addresses By User Test")
    public void getAllAddressesByUserTest() {
        List<Address> actual = addressController.getUserAddress();
        List<Address> expected = List.of(new Address("1 One St.","City","State",1));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Post User Address Test")
    public void postUserAddressTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> responseEntity = addressController.postUserAddress(new Address("1 One St.","City","State",1));
        Assertions.assertEquals(201,responseEntity.getStatusCodeValue());
    }
}
