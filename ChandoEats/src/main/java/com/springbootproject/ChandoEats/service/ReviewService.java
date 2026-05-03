package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.ReviewException;
import com.springbootproject.ChandoEats.Request.ReviewRequest;
import com.springbootproject.ChandoEats.model.Review;
import com.springbootproject.ChandoEats.model.User;

import java.util.List;

public interface ReviewService {
    public Review submitReview(ReviewRequest review , User user);
        public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);

}
