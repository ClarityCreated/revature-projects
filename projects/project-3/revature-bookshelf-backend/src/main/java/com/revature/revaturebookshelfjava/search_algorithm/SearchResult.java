package com.revature.revaturebookshelfjava.search_algorithm;


import com.revature.revaturebookshelfjava.entity.StoreProduct;

import java.util.List;

public interface SearchResult {

    List<StoreProduct> getSearchResultByTitle(List<StoreProduct> bookList, String userInput);
    List<StoreProduct> getSearchResultByAuthor(List<StoreProduct> bookList, String userInput);
    List<StoreProduct> getSearchResultByISBN(List<StoreProduct> bookList, String userInput);

}
