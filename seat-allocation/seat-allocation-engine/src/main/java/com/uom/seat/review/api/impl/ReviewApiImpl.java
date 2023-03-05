package com.uom.seat.review.api.impl;

import com.uom.seat.api.ReviewApi;
import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;
import com.uom.seat.review.logic.ReviewCreationLogic;
import com.uom.seat.review.logic.ReviewDeletionLogic;
import com.uom.seat.review.logic.ReviewRetrievalLogic;
import com.uom.seat.review.logic.ReviewUpdateLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ReviewApiImpl implements ReviewApi {

    @Autowired
    private ReviewCreationLogic reviewCreationLogic;

    @Autowired
    private ReviewRetrievalLogic reviewRetrievalLogic;

    @Autowired
    private ReviewUpdateLogic reviewUpdateLogic;

    @Autowired
    private ReviewDeletionLogic reviewDeletionLogic;


    @Override
    public Integer createReview(String authorization, ReviewRequest review) {
        return reviewCreationLogic.createReview(authorization,review);
    }

    @Override
    public ReviewResponse getReview(String authorization, Integer reviewId) {
        return reviewRetrievalLogic.getReview(authorization,reviewId);
    }

    @Override
    public ReviewResponse updateReview(Integer reviewId, ReviewRequest review) {
        return reviewUpdateLogic.updateReview(reviewId,review);
    }

    @Override
    public Boolean deleteReview(String authorization, Integer reviewId) {
        return reviewDeletionLogic.deleteReview(authorization,reviewId);
    }

}
