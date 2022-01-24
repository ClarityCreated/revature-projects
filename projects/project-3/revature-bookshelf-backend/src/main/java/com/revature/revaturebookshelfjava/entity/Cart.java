package com.revature.revaturebookshelfjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_join",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )

    private List<Book> books;

    public Cart(List<Book> books) {
        this.books = books;
    }
}