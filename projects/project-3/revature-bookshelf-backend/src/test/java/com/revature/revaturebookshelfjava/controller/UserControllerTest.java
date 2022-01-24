package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.authenicator.extractor.UserDetailsExtractor;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.service.CartService;
import com.revature.revaturebookshelfjava.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserControllerTest {

    private static UserController userController;
    private static UserService userServiceMock;
    private static CartService cartServiceMock;
    private UserDetailsExtractor userDetailsExtractorMock;

    @BeforeEach
    public void init() {
        userServiceMock = Mockito.mock(UserService.class);
        cartServiceMock = Mockito.mock(CartService.class);
        userDetailsExtractorMock = Mockito.mock(UserDetailsExtractor.class);
        userController = new UserController(userServiceMock,cartServiceMock,userDetailsExtractorMock);
    }

    @BeforeEach
    public void recordMock() {
        Mockito.when(cartServiceMock.createCart(new Cart())).thenReturn(new Cart());
        Mockito.when(userServiceMock.register(new User())).thenReturn(new User());
    }

    @Test
    @DisplayName("Post User Test")
    public void postUserTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> actual = userController.doRegistration(new User());
        Assertions.assertEquals(201,actual.getStatusCodeValue());
    }

}
