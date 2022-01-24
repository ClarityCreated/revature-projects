package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.authenicator.extractor.UserDetailsExtractor;
import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.exception.CartItemNotExistException;
import com.revature.revaturebookshelfjava.repository.CartRepository;
import com.revature.revaturebookshelfjava.repository.UserRepository;
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

import java.util.List;
import java.util.Optional;

public class CartControllerTest {

    private static CartController cartController;
    private static CartRepository cartRepositoryMock;
    private static CartService cartServiceMock;
    private static UserRepository userRepositoryMock;
    private static UserService userServiceMock;
    private static UserDetailsExtractor userDetailsExtractorMock;

    @BeforeEach
    public void init() {
        cartRepositoryMock = Mockito.mock(CartRepository.class);
        cartServiceMock = Mockito.mock(CartService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userServiceMock = Mockito.mock(UserService.class);
        userDetailsExtractorMock = Mockito.mock(UserDetailsExtractor.class);
        cartController = new CartController(cartRepositoryMock,userRepositoryMock,cartServiceMock,userServiceMock,userDetailsExtractorMock);
    }

    @BeforeEach
    public void recordMock() throws CartItemNotExistException {
        Mockito.when(userDetailsExtractorMock.extractUsername()).thenReturn("username");
        Mockito.when(userServiceMock.getUser("username")).thenReturn(new User());
        Mockito.when(cartServiceMock.getAllItems(new User())).thenReturn(List.of(new Book()));
        Mockito.when(cartServiceMock.addItem(1,new User())).thenReturn(new Cart(List.of(new Book())));
        Mockito.when(cartServiceMock.deleteItem(1,new User())).thenReturn(new Cart());
        Mockito.when(cartServiceMock.deleteItem(2,new User())).thenThrow(CartItemNotExistException.class);
    }

    @Test
    @DisplayName("Get All Items from Cart Test")
    public void getItemsFromCartTest() {
        List<Book> actual = cartController.getCart();
        List<Book> expected = List.of(new Book());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Post Item in Cart Test")
    public void postItemTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> actual = cartController.postItem(1);
        Assertions.assertEquals(201,actual.getStatusCodeValue());
    }

    @Test
    @DisplayName("Successful Delete Item from Cart Test")
    public void successfulDeleteItemTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> actual = cartController.deleteItem(1);
        Assertions.assertEquals(200,actual.getStatusCodeValue());
    }

    @Test
    @DisplayName("Failed Delete Item from Cart Test")
    public void failDeleteItemTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<?> actual = cartController.deleteItem(2);
        Assertions.assertEquals(400,actual.getStatusCodeValue());
    }
}
