package com.uom.seat.review.logic;

import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.service.ReviewService;
import com.uom.seat.review.validator.ReviewValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReviewCreationLogic {
    private static final Logger logger = Logger.getLogger(ReviewCreationLogic.class);
    @Autowired
    private ReviewValidator reviewValidator;

    @Autowired
    private ReviewService reviewService;

    public Integer createReview(String authorization, ReviewRequest review, Integer resourceId) {

            // 1. validate company request
            // 2. create company

            reviewValidator.validateReview(review);
            logger.info("The review is validated.");

            Integer id = reviewService.createReview(review,resourceId);
            logger.info("The review is created.");

            return id;


    }
}
