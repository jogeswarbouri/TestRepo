package com.tavant.mockdrill.repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tavant.mockdrill.model.Deal;
import com.tavant.mockdrill.model.DealIngestion;
import com.tavant.mockdrill.model.LoanData;
import com.tavant.mockdrill.repo.DealSetupRepository;
import com.tavant.mockdrill.repo.LoanRepository;

import junit.framework.Assert;




@RunWith(SpringRunner.class)
@SpringBootTest
public class DealSetupRepositoryTest {

  @Autowired
  private DealSetupRepository userRepository;
  
  @Autowired
  private LoanRepository loanRepository;
  
  @Test
  public void saveDeal() {
    Deal user = new Deal();
    user.setContactId(123);
    user.setClientContact("test");
    user.setDealName("suman");
    DealIngestion dealIngestion = new DealIngestion();
    user.setDealIngestion(dealIngestion);
    
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findAll());
  }
  
  @Test
  public void saveLoan() {
	  
	  Deal user = new Deal();
	    user.setContactId(123);
	    user.setClientContact("test");
	    user.setDealName("suman");
	    DealIngestion dealIngestion = new DealIngestion();
	    user.setDealIngestion(dealIngestion);
	    Deal returnDeal = userRepository.save(user);
		LoanData loan = new LoanData();	
		loan.setLoanId("1");
		loan.setDealIngestion(returnDeal);
		
		loanRepository.save(loan);
		Assert.assertNotNull(loanRepository.findAll());
  }
  
  @Test
  public void findByLoanId() {
	  loanRepository.findByLoanId("1");
		Assert.assertNotNull(loanRepository.findByLoanId("1"));
  }
  
  
}