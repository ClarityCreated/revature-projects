package org.example.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private String image_path;
    @Transient
    @OneToMany(mappedBy="product",cascade=CascadeType.REMOVE)
    private List<RedirectView> reviews;
}
