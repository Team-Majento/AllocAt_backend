package com.uom.seat.review.validator;

import com.uom.seat.review.dto.ReviewRequest;
import org.springframework.stereotype.Component;


@Component
public class ReviewValidator {
    public void validateReview(ReviewRequest review) {
        validateMandatoryFields(review);
        validateOptionalFields(review);
    }

    private void validateMandatoryFields(ReviewRequest review) {
        // TODO

    }

    private void validateOptionalFields(ReviewRequest review) {
        // TODO
    }


}
