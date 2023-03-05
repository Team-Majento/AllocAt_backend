package com.uom.seat.review.logic;


import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;
import com.uom.seat.review.service.ReviewService;
import com.uom.seat.review.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewUpdateLogic {
    @Autowired
    private ReviewService service;

    @Autowired
    private ReviewValidator reviewValidator;



    public ReviewResponse updateReview(Integer id, ReviewRequest review) {
        // 1. find company by id
        // 2. validate company request
        // 3. update company


        reviewValidator.validateReview(review);

        return service.updateReview(id,review);
    }


}
