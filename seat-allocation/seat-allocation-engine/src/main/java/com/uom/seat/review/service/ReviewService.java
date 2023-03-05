package com.uom.seat.review.service;

import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;

public interface ReviewService {
    Integer createReview(ReviewRequest review);

    ReviewResponse getReview(Integer reviewId);

    ReviewResponse updateReview(Integer id, ReviewRequest review);

    Boolean deleteReview(Integer reviewId);
}
