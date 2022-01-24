package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Filter;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService{

    @Override
    public List<StoreProduct> doFilter(List<StoreProduct> books, Filter filter) {
        List<StoreProduct> filteredBooks = new ArrayList<>(books);
        for (StoreProduct book : books) {
            //GENRE FILTER
            if (!filter.getGenres().isEmpty()) {
                for (String genre : filter.getGenres()) {
                    List<String> bookGenres = book.getBook().getGenres().stream()
                            .map(Genre::getName)
                            .collect(Collectors.toList());
                    if (!bookGenres.contains(genre)) {
                        filteredBooks.remove(book);
                    }
                }
            }

            //DATE FILTER
            if (filter.getDates()[0].after(book.getBook().getPublishDate()) || filter.getDates()[1].before(book.getBook().getPublishDate())) {
                filteredBooks.remove(book);
            }

            //PAGECOUNT FILTER
            else if (filter.getPgcounts()[0] > book.getBook().getPageCount() || filter.getPgcounts()[1] < book.getBook().getPageCount()) {
                filteredBooks.remove(book);
            }

            //PRICE FILTER
            else if (filter.getPrices()[0] > book.getPrice() || filter.getPrices()[1] < book.getPrice()) {
                filteredBooks.remove(book);
            }
        }
        return filteredBooks;
    }
}
