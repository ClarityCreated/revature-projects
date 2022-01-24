package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.exception.CartItemNotExistException;

import java.util.List;

public interface CartService {

    Cart createCart(Cart cart);

    Cart addItem(int bookId, User user);

    Cart deleteItem(int bookId, User user) throws CartItemNotExistException;

//    Cart updateItem(Book bookId);

    List<Book> getAllItems(User userId);

}
