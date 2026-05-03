package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.ReviewException;
import com.springbootproject.ChandoEats.Request.ReviewRequest;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.model.Review;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.repository.RestaurantRepository;
import com.springbootproject.ChandoEats.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService{
    @Autowired
    private  ReviewRepository reviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Review submitReview(ReviewRequest reviewRequest, User user) {
        Review review = new Review();
        System.out.println(reviewRequest);

        System.out.println(reviewRequest.getRestaurantId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId());
        if(restaurant.isPresent()) {
            review.setRestaurant(restaurant.get());
        }
        review.setCustomer(user);
        review.setMessage(reviewRequest.getReviewMessage());
        review.setRating(reviewRequest.getRating());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }



    public void deleteReview(Long reviewId) throws ReviewException{
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if(optionalReview.isPresent()){
            reviewRepository.deleteById(reviewId);
        }else{
            throw new ReviewException("Review with Id "+ reviewId +"not found");
        }
    }



    public double calculateAverageRating(List<Review> reviews){
        double totalRating=0;
        for(Review review : reviews){
            totalRating+=review.getRating();
        }
        if(reviews.size()>0){
            return totalRating/reviews.size();
        }else{
            return 0;
        }
    }
}
