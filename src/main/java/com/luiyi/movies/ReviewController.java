package com.luiyi.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<List<Review>>(reviewService.allReviews(), HttpStatus.OK);
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) {
        return new ResponseEntity<String>(reviewService.deleteReview(reviewId), HttpStatus.OK);
    }
}
