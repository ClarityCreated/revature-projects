package com.revature.revaturebookshelfjava.search_algorithm;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchResultImplTest {
    private static SearchResultImpl solidSearchResult;
    private static final List<StoreProduct> bookList = new ArrayList<>();
    private static StringSimilarity stringSimilarityMock;
    private static StringContainment stringContainmentMock;
    private static final Book b1 = new Book("How to Train Your Dog and Make it Love You", 300, new Date(93, Calendar.FEBRUARY, 1), "1-234-56-7890", "John Smith", List.of(new Genre("Nonfiction")), "./dogbook.png");
    private static final Book b2 = new Book("Train Life", 150, new Date(62, Calendar.FEBRUARY, 1), "0-987-65-4321", "Jane Smith", List.of(new Genre("Nonfiction")), "./trainlife.png");
    private static final Book b3 = new Book("Legends of Hawaiian Mythology", 700, new Date(119, Calendar.FEBRUARY, 1), "3-420-76-9158", "Norman Reedus", List.of(new Genre("Mythology")), "./legends.png");

    @BeforeAll
    static void populateBookList() {
        bookList.add(new StoreProduct(1,b1,1,99.99));
        bookList.add(new StoreProduct(2,b2,1,50.50));
        bookList.add(new StoreProduct(3,b3,1,119.99));
    }

    @BeforeEach
    void initiate() {
        stringSimilarityMock = Mockito.mock(StringSimilarity.class);
        stringContainmentMock = Mockito.mock(StringContainment.class);
        solidSearchResult = new SearchResultImpl(stringContainmentMock,stringSimilarityMock);
    }

    @BeforeEach
    void recordMock() {
        //Title test mocks
        Mockito.when(stringSimilarityMock.similarity("train dog","Train Life")).thenReturn(0.6);
        Mockito.when(stringSimilarityMock.similarity("train dog","How to Train Your Dog and Make it Love You")).thenReturn(0.5);
        Mockito.when(stringSimilarityMock.similarity("train dog","Legends of Hawaiian Mythology")).thenReturn(0.20689655172413793);
        Mockito.when(stringContainmentMock.contains("train dog","Train Life")).thenReturn(1);
        Mockito.when(stringContainmentMock.contains("train dog","How to Train Your Dog and Make it Love You")).thenReturn(2);
        Mockito.when(stringContainmentMock.contains("train dog","Legends of Hawaiian Mythology")).thenReturn(0);

        //Author test mocks
        Mockito.when(stringSimilarityMock.similarity("John Smith","John Smith")).thenReturn((double) 1);
        Mockito.when(stringSimilarityMock.similarity("John Smith","Jane Smith")).thenReturn(0.7);
        Mockito.when(stringSimilarityMock.similarity("John Smith","Norman Reedus")).thenReturn(0.23076923076923078);

        //ISBN test mocks
        Mockito.when(stringSimilarityMock.similarity("1-234-56-7890","0-987-65-4321")).thenReturn(0.23);
        Mockito.when(stringSimilarityMock.similarity("1-234-56-7890","3-420-76-9158")).thenReturn(0.9);
        Mockito.when(stringSimilarityMock.similarity("1-234-56-7890","1-234-56-7890")).thenReturn((double) 1);
    }

    @Test
    @DisplayName("Title Search Test")
    public void titleSearchTest() {
        List<StoreProduct> actual = solidSearchResult.getSearchResultByTitle(bookList,"train dog");
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(2,b2,1,50.50));
        expected.add(new StoreProduct(1,b1,1,99.99));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Author Search Test")
    public void authorSearchTest() {
        List<StoreProduct> actual = solidSearchResult.getSearchResultByAuthor(bookList,"John Smith");
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(2,b2,1,50.50));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("ISBN Search Test")
    public void isbnSearchTest() {
        List<StoreProduct> actual = solidSearchResult.getSearchResultByISBN(bookList,"1-234-56-7890");
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(3,b3,1,119.99));
        Assertions.assertEquals(expected,actual);
    }
}
