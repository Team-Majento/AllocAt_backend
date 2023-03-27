package com.uom.seat.api;

import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;

public interface ReviewApi {
    Integer createReview(String authorization, ReviewRequest review, Integer resourceId);

    ReviewResponse getReview(String authorization, Integer reviewId);

    ReviewResponse updateReview(Integer reviewId, ReviewRequest review);

    Boolean deleteReview(String authorization, Integer reviewId);


}
