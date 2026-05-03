package com.springbootproject.ChandoEats.controller;

import com.springbootproject.ChandoEats.Exception.ReviewException;
import com.springbootproject.ChandoEats.Exception.UserException;
import com.springbootproject.ChandoEats.Request.ReviewRequest;
import com.springbootproject.ChandoEats.model.Review;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.service.ReviewService;
import com.springbootproject.ChandoEats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    public UserService userService;

    @Autowired
    public ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest review, @RequestHeader("Authorization") String jwt) throws ReviewException, UserException {
        User user=userService.findUserProfileByJwt(jwt);
        Review submitedReview = reviewService.submitReview(review,user);
        return ResponseEntity.ok(submitedReview);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) throws ReviewException{
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/average-rating")
    public ResponseEntity<Double> calculateAverageRating(@RequestBody List<Review> reviews){
        double averageRating = reviewService.calculateAverageRating(reviews);
        return new ResponseEntity<>(averageRating,HttpStatus.OK);
    }



}
