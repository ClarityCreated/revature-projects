package com.revature.revaturebookshelfjava.search_algorithm;

import com.revature.revaturebookshelfjava.entity.StoreProduct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchResultImpl implements SearchResult{

    private final StringContainment stringContainment;
    private final StringSimilarity stringSimilarity;

    public SearchResultImpl(StringContainment sc, StringSimilarity ss) {
        this.stringContainment = sc;
        this.stringSimilarity = ss;
    }

    public List<StoreProduct> getSearchResultByTitle(List<StoreProduct> bookList, String userInput) {
        List<StoreProduct> similarBooks = new ArrayList<>();
        List<StoreProduct> dissimilarBooks = new ArrayList<>();
        Map<StoreProduct, Double> bookSimilarities = new HashMap<>();
        Map<StoreProduct,Integer> bookContainments = new HashMap<>();
        for (StoreProduct book: bookList) {
            double similarity = stringSimilarity.similarity(userInput,book.getBook().getTitle());
            bookSimilarities.put(book,similarity);
            if (similarity >= 0.40) {
                similarBooks.add(book);
            }
            else {
                dissimilarBooks.add(book);
            }
        }
        List<StoreProduct> dissimilarBooksResult = new ArrayList<>(dissimilarBooks);
        for (StoreProduct book: dissimilarBooks) {
            int containCount = stringContainment.contains(userInput, book.getBook().getTitle());
            if (containCount >= 1) {
                bookContainments.put(book, containCount);
            }
            else {
                dissimilarBooksResult.remove(book);
            }
        }

        similarBooks.sort((o1, o2) -> {
            if (bookSimilarities.get(o1) < bookSimilarities.get(o2)) {
                return 1;
            }
            if (Objects.equals(bookSimilarities.get(o1), bookSimilarities.get(o2))) {
                return 0;
            }
            else {
                return -1;
            }
        });

        dissimilarBooksResult.sort(Comparator.comparingInt(bookContainments::get));
        similarBooks.addAll(dissimilarBooksResult);
        return similarBooks;
    }

    public List<StoreProduct> getSearchResultByAuthor(List<StoreProduct> bookList,String userInput) {
        List<StoreProduct> similarBooks = new ArrayList<>();
        Map<StoreProduct, Double> bookSimilarities = new HashMap<>();
        for (StoreProduct book: bookList) {
            double similarity = stringSimilarity.similarity(userInput,book.getBook().getAuthor());
            bookSimilarities.put(book,similarity);
            if (similarity >= 0.40) {
                similarBooks.add(book);
            }
        }

        similarBooks.sort((o1, o2) -> {
            if (bookSimilarities.get(o1) < bookSimilarities.get(o2)) {
                return 1;
            }
            if (Objects.equals(bookSimilarities.get(o1), bookSimilarities.get(o2))) {
                return 0;
            }
            else {
                return -1;
            }
        });

        return similarBooks;
    }
    public List<StoreProduct> getSearchResultByISBN(List<StoreProduct> bookList,String userInput) {
        List<StoreProduct> similarBooks = new ArrayList<>();
        Map<StoreProduct, Double> bookSimilarities = new HashMap<>();
        for (StoreProduct book: bookList) {
            double similarity = stringSimilarity.similarity(userInput,book.getBook().getIsbn());
            bookSimilarities.put(book,similarity);
            if (similarity >= 0.80) {
                similarBooks.add(book);
            }
        }

        similarBooks.sort((o1, o2) -> {
            if (bookSimilarities.get(o1) < bookSimilarities.get(o2)) {
                return 1;
            }
            if (Objects.equals(bookSimilarities.get(o1), bookSimilarities.get(o2))) {
                return 0;
            }
            else {
                return -1;
            }
        });

        return similarBooks;
    }
}
