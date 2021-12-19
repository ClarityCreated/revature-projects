package org.example.web;

import org.example.entity.Review;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/products/{productId}/reviews",
            produces = {"application/json"}
    )
    public Collection<Review> getAll(){
        Collection<Review> reviews = reviewRepository.findAll();
        return reviews;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/products/{productId}/reviews",
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<?> saveReview(@RequestBody Review review) {
        review = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }
}
