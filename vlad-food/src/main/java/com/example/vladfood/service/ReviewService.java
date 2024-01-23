package com.example.vladfood.service;


import com.example.vladfood.model.Review;
import com.example.vladfood.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void createReview(Review review) {
        reviewRepository.save(review);
    }
}
