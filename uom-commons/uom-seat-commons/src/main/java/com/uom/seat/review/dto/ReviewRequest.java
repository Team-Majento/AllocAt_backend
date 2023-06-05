package com.uom.seat.review.dto;

public class ReviewRequest {

    private Double rating;
    private String reviewText;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
