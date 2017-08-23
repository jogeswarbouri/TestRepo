package com.tavant.springboot.dao;

import java.util.List;

import com.tavant.springboot.bo.UserDetailsBO;

public interface CustomDao {

	public List<String> findAllSkills(Long revId, Long taskId);

	public List<String> findByUsersInSkill(String skill);

	public List<Object[]> getUserByTaskDetails(String username);

	public List<UserDetailsBO> getAllDeal();
}
