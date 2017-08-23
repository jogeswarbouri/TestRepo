package com.tavant.springboot.service;

import java.util.List;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.User;

public interface UserService {

	public User findByUsername(String username, String password);

	public List<UserDetailsBO> getUserByTaskDetails(String username);

	public String getClaimStatus(Long dealId);

	public Deal findByDealId(long dealId);

	public void updateDeal(Deal deal);

}
