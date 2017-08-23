package com.tavant.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.dao.CustomDao;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.Review;
import com.tavant.springboot.model.Task;
import com.tavant.springboot.repository.DealRepository;
import com.tavant.springboot.repository.ReviewRepository;
import com.tavant.springboot.repository.TaskRepository;

@Service("dealService")
@Transactional
public class DealSetUpServiceImpl implements DealSetUpService {

	@Autowired
	private DealRepository dealRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private CustomDao customDao;

	private List<String> skillNames;

	public List<String> getSkillNames() {
		return skillNames;
	}

	public void setSkillNames(List<String> skillNames) {
		this.skillNames = skillNames;
	}

	@Override
	public void saveDealSetUp(Deal deal) {
		dealRepository.save(deal);
		skillNames = customDao.findAllSkills(Long.parseLong(deal.getReviewType()), Long.parseLong(deal.getTask()));
		// getUsersBySkill();
	}

	@Override
	public boolean isDealExist(Deal deal) {
		return findByReviewType(deal.getReviewType()) != null;
	}

	public Deal findByReviewType(String reviewType) {
		return dealRepository.findByReviewType(reviewType);
	}

	@Override
	public void saveReview(Review rev) {
		reviewRepository.save(rev);

	}

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);

	}

	@Override
	public List<Review> findAllReviews() {
		return (List<Review>) reviewRepository.findAll();
	}

	@Override
	public Review findById(String review) {
		return reviewRepository.findByReview(review);
	}

	@Override
	public List<Task> findAllTasks() {
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public List<Task> findAllTasks(Long revId) {
		return taskRepository.getSelectedReviewTask(revId);
	}

	@Override
	public List<Task> findAllTasks(String review) {
		return taskRepository.findByTaskNames(review);
	}

	public Set<String> getUsersBySkill() {
		Set<String> result = new HashSet<>();
		skillNames.forEach(skill -> {
			result.addAll(customDao.findByUsersInSkill(skill));
		});
		return result;
	}

	@Override
	public List<UserDetailsBO> getAllDeal() {		
		return customDao.getAllDeal();
	}

}
