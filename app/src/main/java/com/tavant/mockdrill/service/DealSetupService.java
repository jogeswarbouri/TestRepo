package com.tavant.mockdrill.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tavant.mockdrill.exception.MockInternalException;
import com.tavant.mockdrill.exception.MockResourceException;
import com.tavant.mockdrill.model.Deal;
import com.tavant.mockdrill.model.LoanData;
import com.tavant.mockdrill.model.LoanDetail;


public interface DealSetupService {
	
	public boolean createDealSetup(MultipartFile myFile,String clientContact) throws MockInternalException,MockResourceException;
	public List<LoanDetail> getAllDeals() throws MockResourceException;
	void isLoanExist(List<LoanData> loans, Deal id) throws Exception;

}
	