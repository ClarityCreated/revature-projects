package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement
public class Review {
    @Id
    private int id;
    private String author;
    private int stars;
    private String body;
    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
}
