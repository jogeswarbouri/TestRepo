package com.tavant.mockdrill.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tavant.mockdrill.exception.MockInternalException;
import com.tavant.mockdrill.exception.MockResourceException;
import com.tavant.mockdrill.logger.MockLogger;
import com.tavant.mockdrill.model.Deal;
import com.tavant.mockdrill.model.DealIngestion;
import com.tavant.mockdrill.model.LoanData;
import com.tavant.mockdrill.model.LoanDetail;
import com.tavant.mockdrill.repo.DealSetupRepository;
import com.tavant.mockdrill.repo.LoanRepository;
import com.tavant.mockdrill.service.DealSetupService;
import com.tavant.mockdrill.util.MockUtility;

@Service("dealSetupService")
public class DealSetupServiceImpl implements DealSetupService {

	@Autowired
	private DealSetupRepository dealSetupRepository;

	@Autowired
	private LoanRepository loanRepository;

	public boolean createDealSetup(MultipartFile myFile, String clientContact) throws MockInternalException, MockResourceException {
		MockLogger.entry(MockLogger.getMethodName(), MockLogger.getClassName());
		MockUtility utility = new MockUtility();
		try {
			// Set<LoanData> ingestData = new HashSet<LoanData>();
			List<LoanData> ingestData = new ArrayList<LoanData>();
			ingestData = utility.excelToJson1(myFile);
			JSONObject jsonObj = null;

			jsonObj = new JSONObject(clientContact);
			String dealname = ((null ==jsonObj.getString("dealName")) ? "":jsonObj.getString("dealName"));
			String contactnumber = ((null ==jsonObj.getString("clientContact")) ? "":jsonObj.getString("clientContact"));

			Deal deal = new Deal();
			DealIngestion dealIngestion = new DealIngestion();
			deal.setClientContact(contactnumber);
			deal.setDealName(dealname);
			// deal.setLoanData(ingestData);
			deal.setDealIngestion(dealIngestion);

			Deal dealObj = dealSetupRepository.save(deal);
			// List<LoanData> list = new ArrayList<LoanData>(ingestData);
			isLoanExist(ingestData, dealObj);
		} catch (JSONException e) {
			throw new MockResourceException(e);
		}catch(org.springframework.dao.DataAccessException e){
			throw new MockInternalException(e);
		}catch (Exception e) {
			throw new MockResourceException(e);
		}
		MockLogger.exit(MockLogger.getMethodName(), MockLogger.getClassName());
		return true;
	}

	@Override
	public void isLoanExist(List<LoanData> loans, Deal id) throws Exception{
		MockLogger.entry(MockLogger.getMethodName(), MockLogger.getClassName());
		for (LoanData loan : loans) {
			loan.setDealIngestion(id);
			if (findByLoanId(loan.getLoanId().toString()) == null) {
				loanRepository.save(loan);
			}
		}
		MockLogger.exit(MockLogger.getMethodName(), MockLogger.getClassName());
	}

	private LoanData findByLoanId(String loanId) throws Exception{
		MockLogger.entry(MockLogger.getMethodName(), MockLogger.getClassName());
		return loanRepository.findByLoanId(loanId);
	}

	public List<LoanDetail> getAllDeals() throws MockResourceException {
		MockLogger.entry(MockLogger.getMethodName(), MockLogger.getClassName());
		List<LoanDetail> returndata = new ArrayList<LoanDetail>();
		try{
		List<LoanData> test = loanRepository.findAll();
			for (LoanData testLoandata : test) {
				LoanDetail loandetail = new LoanDetail();
				loandetail.setLoanId(testLoandata.getLoanId() + "");
				loandetail.setResult(testLoandata.getLoanData());
				// loandetail.setjData(testLoandata.getLoanData());
				// JSONObject testObj=testLoandata.getLoanData();
				// int count=testObj.length();
				int count = 0;
				loandetail.setCount(count + "");
				returndata.add(loandetail);
			}
		}catch(Exception e){
			throw new MockResourceException(e);
		}
		MockLogger.exit(MockLogger.getMethodName(), MockLogger.getClassName());
		return returndata;
	}

}
