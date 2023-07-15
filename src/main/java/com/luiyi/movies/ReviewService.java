package com.luiyi.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }
    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
    public String deleteReview(String reviewId) {
        Optional<Review> review = reviewRepository.findReviewByBody(reviewId);
        System.out.println(review);
        if (review.isPresent()) {
            Review _review = review.get();
            //reviewRepository.deleteByBody(reviewId);
            reviewRepository.deleteByBody(reviewId);
            return _review.getBody();
        } else {
            return "No existe review con Id " + reviewId;
        }
    }
}
