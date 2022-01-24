package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Filter;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FilterServiceImplTest {

    private static FilterService filterService;
    private static final List<StoreProduct> bookList = new ArrayList<>();
    private static final Book b1 = new Book("How to Train Your Dog and Make it Love You", 300, new Date(93, Calendar.FEBRUARY, 1), "1-234-56-7890", "John Smith", List.of(new Genre("Nonfiction")), "./dogbook.png");
    private static final Book b2 = new Book("Train Life", 150, new Date(62, Calendar.FEBRUARY, 1), "0-987-65-4321", "Jane Smith", List.of(new Genre("Nonfiction")), "./trainlife.png");
    private static final Book b3 = new Book("Legends of Hawaiian Mythology", 700, new Date(119, Calendar.FEBRUARY, 1), "3-420-76-9158", "Norman Reedus", List.of(new Genre("Mythology")), "./legends.png");

    @BeforeAll
    static void populateBookList() {
        bookList.add(new StoreProduct(1,b1,1,99.99));
        bookList.add(new StoreProduct(2,b2,1,50.50));
        bookList.add(new StoreProduct(3,b3,1,199.99));
    }

    @BeforeEach
    public void init() {
        filterService = new FilterServiceImpl();
    }

    @Test
    @DisplayName("Genre Filter Test")
    public void genreFilterTest() {
        Date[] defaultDates = new Date[2];
        defaultDates[0] = new Date(0,Calendar.JANUARY,1);
        defaultDates[1] = new Date();
        List<String> genres = new ArrayList<>();
        genres.add("Nonfiction");
        long[] defaultPgCounts = new long[2];
        defaultPgCounts[1] = Long.MAX_VALUE;
        double[] defaultPrices = new double[2];
        defaultPrices[0] = 0;
        defaultPrices[1] = Double.MAX_VALUE;
        Filter filter = new Filter(genres,defaultDates,defaultPgCounts,defaultPrices);

        List<StoreProduct> actual = filterService.doFilter(bookList, filter);
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(2,b2,1,50.50));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Page Count Filter Test")
    public void pgcountFilterTest() {
        Date[] defaultDates = new Date[2];
        defaultDates[0] = new Date(0,Calendar.JANUARY,1);
        defaultDates[1] = new Date();
        List<String> genres = new ArrayList<>();
        long[] pgCounts = new long[2];
        pgCounts[0] = 100;
        pgCounts[1] = 400;
        double[] defaultPrices = new double[2];
        defaultPrices[0] = 0;
        defaultPrices[1] = Double.MAX_VALUE;
        Filter filter = new Filter(genres,defaultDates,pgCounts,defaultPrices);

        List<StoreProduct> actual = filterService.doFilter(bookList, filter);
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(2,b2,1,50.50));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Publish Date Filter Test")
    public void dateFilterTest() {
        Date[] dates = new Date[2];
        dates[0] = new Date(50,Calendar.JANUARY,1);
        dates[1] = new Date(100,Calendar.JANUARY,1);
        List<String> genres = new ArrayList<>();
        long[] defaultPgCounts = new long[2];
        defaultPgCounts[1] = Long.MAX_VALUE;
        double[] defaultPrices = new double[2];
        defaultPrices[0] = 0;
        defaultPrices[1] = Double.MAX_VALUE;
        Filter filter = new Filter(genres,dates,defaultPgCounts,defaultPrices);

        List<StoreProduct> actual = filterService.doFilter(bookList, filter);
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(2,b2,1,50.50));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Price Filter Test")
    public void priceFilterTest() {
        Date[] defaultDates = new Date[2];
        defaultDates[0] = new Date(0,Calendar.JANUARY,1);
        defaultDates[1] = new Date();
        List<String> genres = new ArrayList<>();
        long[] defaultPgCounts = new long[2];
        defaultPgCounts[1] = Long.MAX_VALUE;
        double[] prices = new double[2];
        prices[0] = 50.00;
        prices[1] = 100.00;
        Filter filter = new Filter(genres,defaultDates,defaultPgCounts,prices);

        List<StoreProduct> actual = filterService.doFilter(bookList, filter);
        List<StoreProduct> expected = new ArrayList<>();
        expected.add(new StoreProduct(1,b1,1,99.99));
        expected.add(new StoreProduct(2,b2,1,50.50));
        Assertions.assertEquals(expected,actual);
    }

//    @Test
//    @DisplayName("Congregated Filter Test")
//    public void allFilterTest() {
//        Date[] dates = new Date[2];
//        dates[0] = new Date(100,Calendar.JANUARY,1);
//        dates[1] = new Date();
//        List<String> genres = new ArrayList<>();
//        genres.add("Fiction");
//        long[] pgCounts = new long[2];
//        pgCounts[0] = 500;
//        pgCounts[1] = Long.MAX_VALUE;
//        double[] prices = new double[2];
//        prices[0] = 100.00;
//        prices[1] = Double.MAX_VALUE;
//        Filter filter = new Filter(genres,dates,pgCounts,prices);
//
//        System.out.println("Congregated Filter Test");
//        List<Book> actual = filterService.doFilter(bookList, filter);
//        List<Book> expected = new ArrayList<>();
//        expected.add(new Book(3, "Legends of Hawaiian Mythology", 700, new Date(119, Calendar.JANUARY, 1), "3-420-76-9158", "Norman Reedus", "Mythology", "./legends.png", 1,119.99));
//        Assertions.assertEquals(expected,actual);
//    }
}
