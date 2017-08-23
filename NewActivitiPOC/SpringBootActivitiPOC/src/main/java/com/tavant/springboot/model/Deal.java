package com.tavant.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DEALSETUP")
public class Deal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "deal_id")
	private Long dealId;

	@NotEmpty
	@Column(name = "review_id", nullable = false)
	private String reviewType;

	@NotEmpty
	@Column(name = "task_id", nullable = false)
	private String task;

	@NotEmpty
	@Column(name = "priority", nullable = false)
	private String priority;

	@NotEmpty
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "username")
	private String username;
	
    @Column(name = "claim_created_time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
	private Date claimCreatedTimeStamp;    

    @Column(name = "claim_completed_time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
	private Date claimCompletedTimeStamp;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public String getReviewType() {
		return reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getClaimCreatedTimeStamp() {
		return claimCreatedTimeStamp;
	}

	public void setClaimCreatedTimeStamp(Date claimCreatedTimeStamp) {
		this.claimCreatedTimeStamp = claimCreatedTimeStamp;
	}

	public Date getClaimCompletedTimeStamp() {
		return claimCompletedTimeStamp;
	}

	public void setClaimCompletedTimeStamp(Date claimCompletedTimeStamp) {
		this.claimCompletedTimeStamp = claimCompletedTimeStamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Deal [dealId=" + dealId + ", reviewType=" + reviewType + ", task=" + task + ", priority=" + priority
				+ ", status=" + status + ", username=" + username + ", claimCreatedTimeStamp=" + claimCreatedTimeStamp
				+ ", claimCompletedTimeStamp=" + claimCompletedTimeStamp + "]";
	}




}
