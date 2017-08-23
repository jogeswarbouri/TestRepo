package com.tavant.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.dao.CustomDao;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.User;
import com.tavant.springboot.repository.DealRepository;
import com.tavant.springboot.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DealRepository dealRepository;

	@Autowired
	private CustomDao customDao;

	@Override
	public User findByUsername(String username, String password) {
		return userRepository.findByUserDetails(username, password);
	}

	@Override
	public List<UserDetailsBO> getUserByTaskDetails(String username) {

		List<UserDetailsBO> list = new ArrayList<UserDetailsBO>();
		List<Object[]> results = customDao.getUserByTaskDetails(username);
		results.stream().forEach((record) -> {
			UserDetailsBO userDetailsBO = new UserDetailsBO();
			userDetailsBO.setDealId((Integer) record[0]);
			userDetailsBO.setReviewType((String) record[1]);
			userDetailsBO.setTaskName((String) record[2]);
			userDetailsBO.setSla((String) record[3]);
			userDetailsBO.setPriority((String) record[4]);
			userDetailsBO.setStatus((String) record[5]);
			userDetailsBO.setActivityTaskId((String) record[6]);
			
			if (userDetailsBO.getPriority().equals("100")) {
				userDetailsBO.setPriority("High");
			} else if (userDetailsBO.getPriority().equals("50")) {
				userDetailsBO.setPriority("Medium");
			}
			list.add(userDetailsBO);
		});
		return list;
	}

	@Override
	public String getClaimStatus(Long dealId) {
		return dealRepository.findByStatus(dealId);
	}

	@Override
	public Deal findByDealId(long dealId) {
		return dealRepository.findByDealId(dealId);
	}

	@Override
	public void updateDeal(Deal deal) {
		dealRepository.save(deal);
	}
}
