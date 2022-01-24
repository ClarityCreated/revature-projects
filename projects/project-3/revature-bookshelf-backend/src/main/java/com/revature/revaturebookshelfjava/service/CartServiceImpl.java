package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.exception.CartItemNotExistException;
import com.revature.revaturebookshelfjava.repository.BookRepository;
import com.revature.revaturebookshelfjava.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;

    public CartServiceImpl(CartRepository cartRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    @Override
    public Cart addItem(int bookId, User user) {
        // These Optionals should always be true
        Optional<Cart> cartOptional = getCartByUser(user);
        Cart cart = cartOptional.get();
        Optional<Book> book = bookRepository.findById(bookId);
        cart.getBooks().add(book.get());
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteItem(int bookId, User user) {
        // These Optionals should always be present
        Optional<Cart> cartOptional = getCartByUser(user);
        Cart cart = cartOptional.get();
        Optional<Book> book = bookRepository.findById(bookId);
        cart.getBooks().remove(book.get());
        return cartRepository.save(cart);

    }

//    @Override
//    public Cart updateItem(Book bookId) {
//        Cart cart = updateItem(bookId);
//        return cartRepository.save(cart);
//    }

    @Override
    public List<Book> getAllItems(User user) {
        Optional<Cart> cartOptional = getCartByUser(user);
        return cartOptional.get().getBooks();
    }

    public Optional<Cart> getCartByUser(User user) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(user.getId());
        if (cartOptional.isEmpty()) {
            throw new NotFoundException("SHOULD NEVER BE THROWN");
        }
        return cartOptional;
    }
}
