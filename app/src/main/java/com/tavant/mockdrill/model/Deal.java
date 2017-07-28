package com.tavant.mockdrill.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="deal")
public class Deal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum Status {
	    YES,
	    NO,
	    NOT_SURE
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "deal_id")
	private Integer dealId;
	
	@Column(name = "deal_name")
	private String dealName;
	
	@Column(name = "client_contact")
	private String clientContact;
	
	@Column(name = "contract_id")
	private Integer contactId;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="deal_ingestion_id")
	private DealIngestion dealIngestion;
	
	 @OneToMany(mappedBy = "dealIngestion1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<LoanData> loanData= new HashSet<LoanData>(0);

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getClientContact() {
		return clientContact;
	}

	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public DealIngestion getDealIngestion() {
		return dealIngestion;
	}

	public void setDealIngestion(DealIngestion dealIngestion) {
		this.dealIngestion = dealIngestion;
	}

	public Set<LoanData> getLoanData() {
		return loanData;
	}

	public void setLoanData(Set<LoanData> loanData) {
		this.loanData = loanData;
	}
}

