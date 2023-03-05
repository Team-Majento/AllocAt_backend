package com.uom.seat.review.logic;


import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.review.dto.ReviewResponse;
import com.uom.seat.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewRetrievalLogic {

    @Autowired
    private ReviewService reviewService;

    public ReviewResponse getReview(String authorization, Integer reviewId) {
        return  reviewService.getReview(reviewId);

    }

    private void validateServiceParam(Integer id) {
        //TODO
        //ValidationUtil.validateNotEmpty(id, "The id is mandatory");
    }


}
