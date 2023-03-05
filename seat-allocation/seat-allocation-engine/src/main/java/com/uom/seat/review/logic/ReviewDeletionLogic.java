package com.uom.seat.review.logic;

import com.uom.seat.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewDeletionLogic {

    @Autowired
    private ReviewService reviewService;


    public Boolean deleteReview(String accessToken, Integer reviewId) {
        return  reviewService.deleteReview(reviewId);
   }



}
