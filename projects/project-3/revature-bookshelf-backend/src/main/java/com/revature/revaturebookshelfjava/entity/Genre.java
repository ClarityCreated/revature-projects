package com.revature.revaturebookshelfjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
