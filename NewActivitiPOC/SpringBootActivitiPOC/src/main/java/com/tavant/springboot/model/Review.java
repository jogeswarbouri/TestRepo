package com.tavant.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rev_id")
	private Long revId;

	@NotEmpty
	@Column(name = "review_type", nullable = false)
	private String review;

	public Long getRevId() {
		return revId;
	}

	public void setRevId(Long revId) {
		this.revId = revId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Review [revId=" + revId + ", review=" + review + "]";
	}

}
