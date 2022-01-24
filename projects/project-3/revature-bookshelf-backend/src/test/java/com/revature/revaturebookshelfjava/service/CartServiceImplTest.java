package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.repository.BookRepository;
import com.revature.revaturebookshelfjava.repository.CartRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartServiceImplTest {

    private static CartServiceImpl cartService;
    private static CartRepository cartRepositoryMock;
    private static BookRepository bookRepositoryMock;
    private static final List<Book> EMPTY_LIST = new ArrayList<>();
    private static List<Book> full_list = new ArrayList<>();

    @BeforeAll
    public static void populateFullList() {
        full_list.add(new Book());
    }

    @BeforeEach
    public void init() {

        cartRepositoryMock = Mockito.mock(CartRepository.class);
        bookRepositoryMock = Mockito.mock(BookRepository.class);
        cartService = new CartServiceImpl(cartRepositoryMock,bookRepositoryMock);
    }

    @BeforeEach
    public void recordMock() {
        Mockito.when(bookRepositoryMock.findById(1)).thenReturn(Optional.of(new Book()));
        Mockito.when(cartRepositoryMock.findByUserId(1)).thenReturn(Optional.of(new Cart(EMPTY_LIST)));
        Mockito.when(cartRepositoryMock.save(new Cart(full_list))).thenReturn(new Cart(full_list));

        Mockito.when(cartRepositoryMock.findByUserId(2)).thenReturn(Optional.of(new Cart(full_list)));
        Mockito.when(cartRepositoryMock.save(new Cart())).thenReturn(new Cart());

        Mockito.when(cartRepositoryMock.findByUserId(3)).thenReturn(Optional.empty());
    }

    @Disabled
    @Test
    @DisplayName("Add Item Cart Test")
    public void addItemTest() {
        User user = new User();
        user.setId(1);
        Cart actualCart = cartService.addItem(1,user);
        Cart expectedCart = new Cart(full_list);
        Assertions.assertEquals(expectedCart,actualCart);
    }

    @Test
    @DisplayName("Delete Item Cart Test")
    public void deleteItemTest() {
        User user = new User();
        user.setId(2);
        Cart actualCart = cartService.deleteItem(1,user);
        Cart expectedCart = new Cart(EMPTY_LIST);
        Assertions.assertEquals(expectedCart,actualCart);
    }

    @Test
    @DisplayName("Should Never Be Thrown Exception Test")
    public void getCartByUserExceptionTest() {
        User user = new User();
        user.setId(3);
        Exception exception = assertThrows(Exception.class, () -> cartService.getCartByUser(user));
        String expected = "SHOULD NEVER BE THROWN";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Get Cart By User Test")
    public void getCartByUserTest() {
        User user = new User();
        user.setId(1);
        Optional<Cart> actual = cartService.getCartByUser(user);
        Optional<Cart> expected = Optional.of(new Cart(EMPTY_LIST));
        Assertions.assertEquals(expected,actual);
    }

}
