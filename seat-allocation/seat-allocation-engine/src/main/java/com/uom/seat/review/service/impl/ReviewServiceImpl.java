package com.uom.seat.review.service.impl;
import com.uom.seat.company.entity.CompanyEntity;
import com.uom.seat.resource.entity.ResourceEntity;
import com.uom.seat.resource.repository.ResourceRepository;
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
    private ResourceRepository resourceRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer createReview(ReviewRequest review, Integer resourceId) {
        return reviewRepository.save(convertToReviewEntity(review,resourceId)).getReviewId();
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

    private ReviewEntity convertToReviewEntity(ReviewRequest review, Integer resourceId) {
        ResourceEntity resourceEntity= resourceRepository.findById(resourceId).get();
       ReviewEntity entity= null;
        entity = modelMapper.map(review, ReviewEntity.class);
        entity.setResourceEntity(resourceEntity);
        return entity;
    }
}
