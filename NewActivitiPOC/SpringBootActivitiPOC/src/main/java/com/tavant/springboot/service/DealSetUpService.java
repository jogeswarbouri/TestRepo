package com.tavant.springboot.service;

import java.util.List;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.Review;
import com.tavant.springboot.model.Task;

public interface DealSetUpService {

	public void saveDealSetUp(Deal deal);

	public boolean isDealExist(Deal deal);

	public Deal findByReviewType(String reviewType);

	public List<Review> findAllReviews();

	public List<Task> findAllTasks();

	public List<Task> findAllTasks(Long revId);

	public void saveReview(Review rev);

	public void saveTask(Task task);

	public Review findById(String review);

	public List<Task> findAllTasks(String review);

	public List<UserDetailsBO> getAllDeal();

}
