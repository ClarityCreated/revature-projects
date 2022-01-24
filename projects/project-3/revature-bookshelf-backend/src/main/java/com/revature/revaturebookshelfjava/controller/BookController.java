package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Filter;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import com.revature.revaturebookshelfjava.exception.InvalidSearchPropertyException;
import com.revature.revaturebookshelfjava.repository.BookRepository;
import com.revature.revaturebookshelfjava.search_algorithm.SearchResult;
import com.revature.revaturebookshelfjava.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookController {


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private FilterService filterService;
    @Autowired
    private SearchResult searchResult;
    private List<StoreProduct> products = null;

    public BookController(BookRepository bookRepository, FilterService filterService, SearchResult searchResult) {
        this.bookRepository = bookRepository;
        this.filterService = filterService;
        this.searchResult = searchResult;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/available")
    public List<StoreProduct> getAvailableBooks() {
//        return bookRepository.findAllAvailable();
        return bookRepository.findAllAvailable();
    }

    @GetMapping("/books/{_genre}")
    public List<Book> getSelectGenre(@PathVariable String _genre) {
        _genre = _genre.replaceAll("[_]"," ");
        return bookRepository.findSelectGenre(_genre);
    }

    @GetMapping("/books/genres")
    public List<Genre> getAllGenres() {
//        List<Genre> genres = new ArrayList<>();
//        List<Genre> allGenres = new ArrayList<>();
//
//        for(int i=0; i<bookRepository.findAllGenres().size(); i++){
//            genres.add(i,bookRepository.findAllGenres().get(i));
//        }
//
//        for(int i=0; i<genres.size(); i++){
//            allGenres.add(genres.get(i).substring(11,genres.get(i).lastIndexOf("}")-1));
//        }
//
//        return allGenres.stream().distinct().collect(Collectors.toList());

        return bookRepository.findAllGenres();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/books/searchlist/{searchproperty}/{searchinput}")
    public List<StoreProduct> getSearchResult(@RequestBody Filter filter, @PathVariable("searchproperty") String searchProperty, @PathVariable("searchinput") String userInput) throws InvalidSearchPropertyException {
        userInput = userInput.replaceAll("[_]"," ");
        products = bookRepository.findAllAvailable();
        List<StoreProduct> filteredBooks = filterService.doFilter(products,filter);
        if (searchProperty.equalsIgnoreCase("title")) {
            return searchResult.getSearchResultByTitle(filteredBooks,userInput);
        }

        else if (searchProperty.equalsIgnoreCase("author")) {
            return searchResult.getSearchResultByAuthor(filteredBooks,userInput);
        }
        else if (searchProperty.equalsIgnoreCase("ISBN")) {
            return searchResult.getSearchResultByISBN(filteredBooks,userInput);
        }
        else {
            throw new InvalidSearchPropertyException("Search property is invalid. It must be \"title\", \"author\", or \"ISBN\"");
        }
    }

}
