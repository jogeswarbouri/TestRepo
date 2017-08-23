package com.tavant.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	public Review findByReview(String review);

}
