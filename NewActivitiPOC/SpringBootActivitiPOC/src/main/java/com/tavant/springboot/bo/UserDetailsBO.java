package com.tavant.springboot.bo;

import java.util.Date;

public class UserDetailsBO {

	private Integer dealId;

	private String reviewType;

	private String sla;

	private String taskName;

	private String priority;

	private String status;
	
	private String activityTaskId;
	
	private String username;
	
	private Date claimCompletedTimeStamp;
	
	private Date claimCreatedTimeStamp;

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

	public String getReviewType() {
		return reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivityTaskId() {
		return activityTaskId;
	}

	public void setActivityTaskId(String activityTaskId) {
		this.activityTaskId = activityTaskId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getClaimCompletedTimeStamp() {
		return claimCompletedTimeStamp;
	}

	public void setClaimCompletedTimeStamp(Date claimCompletedTimeStamp) {
		this.claimCompletedTimeStamp = claimCompletedTimeStamp;
	}

	public Date getClaimCreatedTimeStamp() {
		return claimCreatedTimeStamp;
	}

	public void setClaimCreatedTimeStamp(Date claimCreatedTimeStamp) {
		this.claimCreatedTimeStamp = claimCreatedTimeStamp;
	}

	@Override
	public String toString() {
		return "UserDetailsBO [dealId=" + dealId + ", reviewType=" + reviewType + ", sla=" + sla + ", taskName="
				+ taskName + ", priority=" + priority + ", status=" + status + ", activityTaskId=" + activityTaskId
				+ ", username=" + username + ", claimCompletedTimeStamp=" + claimCompletedTimeStamp
				+ ", claimCreatedTimeStamp=" + claimCreatedTimeStamp + "]";
	}
	



}
