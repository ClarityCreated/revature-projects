package com.revature.revaturebookshelfjava.repository;


import com.revature.revaturebookshelfjava.entity.Book;
import com.revature.revaturebookshelfjava.entity.Genre;
import com.revature.revaturebookshelfjava.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("from StoreProduct")
    List<StoreProduct> findAllAvailable();

    @Query("from Book b join b.genres g where g.name=:genre_name")
    List<Book> findSelectGenre(String genre_name);

    @Query("from Genre")
    List<Genre> findAllGenres();

}
