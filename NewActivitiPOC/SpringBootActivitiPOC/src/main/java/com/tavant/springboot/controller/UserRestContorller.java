package com.tavant.springboot.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.springboot.bo.UserDetailsBO;
import com.tavant.springboot.constant.ConstantValue;
import com.tavant.springboot.model.Deal;
import com.tavant.springboot.model.User;
import com.tavant.springboot.service.UserService;
import com.tavant.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/user")
public class UserRestContorller {

	@Autowired
	private UserService userService;

	@Autowired
	private ProcessEngineConfiguration processEngine;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> validateAdminUser(@RequestBody User user) {
		User users = null;
		if (ConstantValue.userName.equalsIgnoreCase(user.getUsername())) {
			users = this.userService.findByUsername(user.getUsername(), user.getPassword());
		}
		if (users == null)
			return new ResponseEntity<String>("This is not " + ConstantValue.userName + " user ", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>("Success login " + user.getUsername() + " user ", HttpStatus.OK);
	}

	@RequestMapping(value = "/otherUsers", method = RequestMethod.POST)
	public List<UserDetailsBO> validateOtherUser(@RequestBody User user) {
		User users = null;
		List<UserDetailsBO> userTaskDetails = new ArrayList<UserDetailsBO>();
		if (!ConstantValue.userName.equalsIgnoreCase(user.getUsername()))
			users = this.userService.findByUsername(user.getUsername(), user.getPassword());
		if (users == null) {
			return userTaskDetails;
		} else {
			userTaskDetails = this.userService.getUserByTaskDetails(user.getUsername());
			System.out.println(userTaskDetails);
			return userTaskDetails;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/userClaim", method = RequestMethod.POST)
	public ResponseEntity<?> claimByUser(@RequestBody UserDetailsBO userDetails) {
		Date date = new Date();
		TaskService taskService = processEngine.getTaskService();
		Deal currentDeal = this.userService.findByDealId(userDetails.getDealId().longValue());
		if (currentDeal == null) {
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to update. Deal with id " + userDetails.getDealId().longValue() + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (ConstantValue.statusInitiated.equalsIgnoreCase(currentDeal.getStatus())) {
			currentDeal.setStatus(ConstantValue.statusProgress);
			currentDeal.setClaimCreatedTimeStamp(new Timestamp(date.getTime()));
			currentDeal.setUsername(userDetails.getUsername());
			taskService.claim(userDetails.getActivityTaskId(), userDetails.getUsername());
			this.userService.updateDeal(currentDeal);
			/*  System.out.println(taskService.getIdentityLinksForTask("9").get(0));
			  List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userDetails.getUsername()).list();
			for (Task task :tasks)
			{ 
				System.out.println("Task available: " + task.getName()+
				  " "+ task.getId());
				  if(userDetails.getActivityTaskId().equals(task.getId()))
				  taskService.claim(task.getId(), userDetails.getUsername()); 
				  }*/
			
			return new ResponseEntity<Deal>(currentDeal, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Unable to update. Deal with id "
				+ userDetails.getDealId().longValue() + " Allrady updated " + currentDeal.getStatus() + " found."),
				HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/completeClaim", method = RequestMethod.POST)
	public ResponseEntity<?> completeClaimByUser(@RequestBody UserDetailsBO userDetails) {
		Date date = new Date();
		TaskService taskService = processEngine.getTaskService();
		Deal currentDeal = this.userService.findByDealId(userDetails.getDealId().longValue());
		if (currentDeal == null) {
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to update. Deal with id " + userDetails.getDealId().longValue() + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if (ConstantValue.statusInitiated.equalsIgnoreCase(currentDeal.getStatus())) {
			return new ResponseEntity(new CustomErrorType("Please first claim Deal with id "
					+ userDetails.getDealId().longValue() + " current status found " + ConstantValue.statusInitiated),
					HttpStatus.NOT_FOUND);
		} else if (ConstantValue.statusProgress.equalsIgnoreCase(currentDeal.getStatus())) {
			currentDeal.setStatus(ConstantValue.statusCompleted);
			currentDeal.setClaimCompletedTimeStamp(new Timestamp(date.getTime()));
			taskService.complete(userDetails.getActivityTaskId());
			this.userService.updateDeal(currentDeal);

			// System.out.println(taskService.getIdentityLinksForTask("9").get(0));
			/*
			 * List<Task> tasks =
			 * taskService.createTaskQuery().taskCandidateUser("userA").list();
			 * List<Task> tasks1 =
			 * taskService.createTaskQuery().taskAssignee("userA").list();
			 * List<Task> tasks2 =
			 * taskService.createTaskQuery().taskCandidateOrAssigned("userA").
			 * list(); System.out.println("tasks.size()"+tasks2.size()); for
			 * (Task task : tasks2) { System.out.println("Task available: " +
			 * task.getName()+ " "+ task.getId());
			 * taskService.complete(userDetails.getActivityTaskId()); }
			 */
			return new ResponseEntity<Deal>(currentDeal, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Unable to update. Deal with id "
				+ userDetails.getDealId().longValue() + " Allrady updated " + currentDeal.getStatus() + " found."),
				HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/rejectClaimByAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> rejectClaimByAdminUser(@RequestBody UserDetailsBO userDetails) {
		TaskService taskService = processEngine.getTaskService();
		Deal currentDeal = this.userService.findByDealId(userDetails.getDealId().longValue());
		if (currentDeal == null) {
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to update. Deal with id " + userDetails.getDealId().longValue() + " not found."),
					HttpStatus.NOT_FOUND);
		} else if (ConstantValue.statusInitiated.equalsIgnoreCase(currentDeal.getStatus())) {
			return new ResponseEntity(new CustomErrorType("Please first claim Deal with id "
					+ userDetails.getDealId().longValue() + " current status found " + ConstantValue.statusInitiated),
					HttpStatus.NOT_FOUND);
		} else if (ConstantValue.statusProgress.equalsIgnoreCase(currentDeal.getStatus())) {
			currentDeal.setStatus(ConstantValue.statusInitiated);
			currentDeal.setClaimCreatedTimeStamp(null);
			taskService.unclaim(userDetails.getActivityTaskId());
			this.userService.updateDeal(currentDeal);
			return new ResponseEntity<Deal>(currentDeal, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Unable to update. Deal with id "
				+ userDetails.getDealId().longValue() + " Allrady updated " + currentDeal.getStatus() + " found."),
				HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/revertClaimByUser", method = RequestMethod.POST)
	public ResponseEntity<?> revertBackClaimByUser(@RequestBody UserDetailsBO userDetails) {
		TaskService taskService = processEngine.getTaskService();
		Deal currentDeal = this.userService.findByDealId(userDetails.getDealId().longValue());
		if (currentDeal == null) {
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to update. Deal with id " + userDetails.getDealId().longValue() + " not found."),
					HttpStatus.NOT_FOUND);
		} else if (ConstantValue.statusInitiated.equalsIgnoreCase(currentDeal.getStatus())) {
			return new ResponseEntity(new CustomErrorType("Please first claim Deal with id "
					+ userDetails.getDealId().longValue() + " current status found " + ConstantValue.statusInitiated),
					HttpStatus.NOT_FOUND);
		} else if (ConstantValue.statusProgress.equalsIgnoreCase(currentDeal.getStatus())) {
			currentDeal.setStatus(ConstantValue.statusInitiated);
			currentDeal.setClaimCreatedTimeStamp(null);
			taskService.unclaim(userDetails.getActivityTaskId());
			this.userService.updateDeal(currentDeal);
			return new ResponseEntity<Deal>(currentDeal, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Unable to update. Deal with id "
				+ userDetails.getDealId().longValue() + " Allrady updated " + currentDeal.getStatus() + " found."),
				HttpStatus.NOT_FOUND);
	}

}
