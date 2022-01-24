package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Filter;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import com.revature.revaturebookshelfjava.exception.InvalidSearchPropertyException;
import com.revature.revaturebookshelfjava.repository.BookRepository;
import com.revature.revaturebookshelfjava.search_algorithm.SearchResult;
import com.revature.revaturebookshelfjava.service.FilterService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class BookControllerTest {

    private static BookController bookController;
    private static BookRepository bookRepositoryMock;
    private static FilterService filterServiceMock;
    private static SearchResult searchResultMock;

    @BeforeEach
    public void init() {
        bookRepositoryMock = Mockito.mock(BookRepository.class);
        filterServiceMock = Mockito.mock(FilterService.class);
        searchResultMock = Mockito.mock(SearchResult.class);
        bookController = new BookController(bookRepositoryMock, filterServiceMock, searchResultMock);
    }

    @BeforeEach
    public void recordMock() {
        Mockito.when(bookRepositoryMock.findAllAvailable())
                .thenReturn(List.of(new StoreProduct(new Book(), 1, 9.99), new StoreProduct(new Book(), 2, 5.99)));
        Mockito.when(bookRepositoryMock.findAll()).thenReturn(List.of(new Book()));
        Mockito.when(bookRepositoryMock.findSelectGenre("genre")).thenReturn(List.of(new Book()));
        Mockito.when(bookRepositoryMock.findAllGenres()).thenReturn(List.of(new Genre()));
        Mockito.when(filterServiceMock.doFilter(List.of(new StoreProduct(new Book(), 1, 9.99), new StoreProduct(new Book(), 2, 5.99)), new Filter()))
                .thenReturn(List.of(new StoreProduct(new Book(), 1, 9.99), new StoreProduct(new Book(), 2, 5.99)));
        Mockito.when(searchResultMock.getSearchResultByTitle(List.of(new StoreProduct(new Book(), 1, 9.99), new StoreProduct(new Book(), 2, 5.99)), "input"))
                .thenReturn(List.of(new StoreProduct(new Book(), 1, 9.99)));
    }

    @Disabled
    @Test
    @DisplayName("Find All Store Products Test")
    public void findAllAvailableTest() {
        List<StoreProduct> actual = bookController.getAvailableBooks();
        List<StoreProduct> expected = List.of(new StoreProduct(new Book(), 1, 9.99), new StoreProduct(new Book(), 2, 5.99));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find All Books Test")
    public void findAllBooksTest() {
        List<Book> actual = bookController.getBooks();
        List<Book> expected = List.of(new Book());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get Books By Genre Test")
    public void getBooksByGenreTest() {
        List<Book> actual = bookController.getSelectGenre("genre");
        List<Book> expected = List.of(new Book());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get All Genres Test")
    public void getAllGenresTest() {
        List<Genre> actual = bookController.getAllGenres();
        List<Genre> expected = List.of(new Genre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Successful Search Controller Test")
    public void searchControllerTest() {
        try {
            List<StoreProduct> actual = bookController.getSearchResult(new Filter(), "title", "input");
            List<StoreProduct> expected = List.of(new StoreProduct(new Book(), 1, 9.99));
        } catch (Exception e) {
            fail("Exception unexpectedly thrown: " + e.getMessage());
        }
    }

//    @Test
//    @DisplayName("Invalid Search Property Controller Test")
//    public void invalidSearchPropertyTest() {
//        InvalidSearchPropertyException exception = assertThrows(InvalidSearchPropertyException.class, () -> bookController.getSearchResult(new Filter(), "invalid", "input"));
//        String actual = exception.getMessage();
//        String expected = "Search property is invalid. It must be \"title\", \"author\", or \"ISBN\"";
//        Assertions.assertEquals(expected, actual);
//    }
}

