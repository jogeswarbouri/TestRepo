package com.tavant.mockdrill.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan_raw_data")
public class LoanData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "loan_id")
	private String loanId;

	/*
	 * @Column(name = "DEAL_ID") private Integer dealId;
	 */

	@Column(name = "loan_data")
	//@Convert(converter = JpaConverterJson.class)
	private String loanData;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deal_id", nullable = false)
	private Deal dealIngestion1;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	/*
	 * public Integer getDealId() { return dealId; }
	 * 
	 * public void setDealId(Integer dealId) { this.dealId = dealId; }
	 */
	public String getLoanData() {
		return loanData;
	}

	public void setLoanData(String loanData) {
		this.loanData = loanData;
	}

	

	@Override
	public String toString() {
		return "LoanData [loanId=" + loanId + ", loanData=" + loanData
				+ ", dealIngestion=" + dealIngestion1
				+ "]";
	}

	public Deal getDealIngestion() {
		return dealIngestion1;
	}

	public void setDealIngestion(Deal dealIngestion) {
		this.dealIngestion1 = dealIngestion;
	}

	

}

