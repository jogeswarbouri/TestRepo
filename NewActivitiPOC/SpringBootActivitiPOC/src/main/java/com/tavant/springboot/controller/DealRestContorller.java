package com.tavant.springboot.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.constant.ConstantValue;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.Review;
import com.tavant.springboot.model.Task;
import com.tavant.springboot.repository.ReviewRepository;
import com.tavant.springboot.repository.TaskRepository;
import com.tavant.springboot.service.DealSetUpService;

@RestController
@RequestMapping("/deal")
public class DealRestContorller {

	private Long taskId;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private DealSetUpService dealService;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private RuntimeService runtimeService;

	@RequestMapping(value = "/createDeal", method = RequestMethod.POST)
	public String createDeal(@RequestBody Deal deal) {
		String taskName;
		String reviewName = deal.getReviewType();
		Review review = reviewRepository.findByReview(reviewName);
		taskName = deal.getTask();
		Long reviewId = review.getRevId();

		List<Task> task = taskRepository.getSelectedReviewTask(reviewId);
		for (Task t : task) {
			if (t.getTaskName().equals(taskName) && t.getReview().getRevId() == reviewId) {
				taskId = t.getTaskId();
			}
		}
		deal.setReviewType(Long.toString(review.getRevId()));
		deal.setTask(Long.toString(taskId));
		deal.setStatus(ConstantValue.statusInitiated);
		dealService.saveDealSetUp(deal);
		Map<String, Object> vars = Collections.<String, Object>singletonMap("deal", deal);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("userAssign", vars);
		return "successfully submited.";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> getAllReview() {
		List<Review> reviews = dealService.findAllReviews();
		System.out.println(reviews);
		if (reviews.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/reviewType", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllReviewType() {
		List<Review> reviews = dealService.findAllReviews();
		List<String> reviewTypes = reviews.stream().map(x -> x.getReview()).collect(Collectors.toList());
		if (reviewTypes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(reviewTypes, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> getAllTask() {
		List<Task> tasks = dealService.findAllTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/taskByName/{revId}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllTaskName(@PathVariable("revId") String revId) {
		System.out.println(revId);
		List<Task> task = dealService.findAllTasks(Long.parseLong(revId));
		List<String> taskName = task.stream().map(x -> x.getTaskName()).collect(Collectors.toList());
		if (taskName.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(taskName, HttpStatus.OK);
	}

	@RequestMapping(value = "/createReview", method = RequestMethod.POST)
	public String createReview(@RequestBody Review review) {
		dealService.saveReview(review);
		return "success create. A Deal Setup with review " + review.getReview();
	}

	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	public String createTask(@RequestBody Task task) {
		dealService.saveTask(task);
		return "success create. A Deal Setup with name " + task.getTaskName();
	}

	@RequestMapping(value = "/getAllDeal", method = RequestMethod.GET)
	public List<UserDetailsBO> getAllDeal() {
		List<UserDetailsBO> userTaskDetails = new ArrayList<UserDetailsBO>();
		userTaskDetails = this.dealService.getAllDeal();
		return userTaskDetails;
	}

}