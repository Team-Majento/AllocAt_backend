package com.uom.seat.review.service.impl;
import com.uom.seat.review.dto.ReviewRequest;
import com.uom.seat.review.dto.ReviewResponse;
import com.uom.seat.review.entity.ReviewEntity;
import com.uom.seat.review.repository.ReviewRepository;
import com.uom.seat.review.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer createReview(ReviewRequest review) {
        return reviewRepository.save(convertToReviewEntity(review)).getReviewId();
    }

    @Override
    public ReviewResponse getReview(Integer reviewId) {
        return convertToReviewResponse(reviewRepository.findById(reviewId).get());
    }

    @Override
    public ReviewResponse updateReview(Integer id, ReviewRequest review) {
        ReviewEntity entity = reviewRepository.saveAndFlush(updateReviewEntity(reviewRepository.findById(id).get(),review));
        return convertToReviewResponse(entity);
    }

    @Override
    public Boolean deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId); ;
        return true;
    }

    private ReviewEntity updateReviewEntity(ReviewEntity reviewEntity, ReviewRequest review) {
        reviewEntity.setRating(review.getRating());
        reviewEntity.setReviewText(review.getReviewText());
        return  reviewEntity;
    }

    private ReviewResponse convertToReviewResponse(ReviewEntity reviewEntity) {
        ReviewResponse dto = null;
        dto = modelMapper.map(reviewEntity, ReviewResponse.class);
        return dto;
    }

    private ReviewEntity convertToReviewEntity(ReviewRequest review) {
       ReviewEntity entity= null;
        entity = modelMapper.map(review, ReviewEntity.class);
        return entity;
    }
}
