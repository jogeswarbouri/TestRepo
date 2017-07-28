package com.tavant.mockdrill.model;

import org.json.JSONObject;

public class LoanDetail {

	private String loanId;
	private JSONObject jData;
	private String result;
	private String count;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public JSONObject getjData() {
		return jData;
	}

	public void setjData(JSONObject jData) {
		this.jData = jData;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public LoanDetail() {
	}

	public LoanDetail(String loanId, String result, String count) {
		super();
		this.loanId = loanId;
		this.result = result;
		this.count = count;
	}

	
}
