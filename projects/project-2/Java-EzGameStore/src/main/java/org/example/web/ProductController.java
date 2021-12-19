package org.example.web;


import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(
            value = "/api/products",
            produces = {"application/json"}
    )
    public Collection<Product> getAll() {
        Collection<Product> products = productRepository.findAll();
        return products;
    }

    @GetMapping(
            value = "/api/products/{productId}",
            produces = {"application/json"}
    )
    public ResponseEntity<?> get(@PathVariable(name = "productId") int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @PostMapping(
            value = "/api/products",
            consumes = {"application/json"}
    )
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        product = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }
    @PutMapping(
            value = "/api/products/{productId}"
    )
    public ResponseEntity<?> updateProduct(@PathVariable(name = "productId") int productId,
            @RequestBody Product product
    ) {
        product.setId(productId);
        product = productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(

            value = "/api/products/{productId}"
    )
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") int productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }


}
