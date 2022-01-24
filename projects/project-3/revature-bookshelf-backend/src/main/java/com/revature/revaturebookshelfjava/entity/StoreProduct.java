package com.revature.revaturebookshelfjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "store_inventory")
public class StoreProduct {

    @Id
    @Column(name = "book_id")
    private int id;
    @OneToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;
    private double price;


    public StoreProduct(Book book, int quantity, double price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }
}
