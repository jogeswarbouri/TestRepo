package com.tavant.mockdrill.serviceTest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.tavant.mockdrill.exception.MockResourceException;
import com.tavant.mockdrill.model.Deal;
import com.tavant.mockdrill.model.DealIngestion;
import com.tavant.mockdrill.model.LoanData;
import com.tavant.mockdrill.repo.DealSetupRepository;
import com.tavant.mockdrill.repo.LoanRepository;
import com.tavant.mockdrill.service.impl.DealSetupServiceImpl;

/*
 @RunWith(SpringRunner.class)
 @WebMvcTest(DealSetupServiceImpl.class)*/
public class DealSetUpServiceTest {

	@Mock
	private DealSetupRepository dealSetupRepository;
	@Mock
	private LoanRepository loanRepository;
	@InjectMocks
	private DealSetupServiceImpl dealSetupService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void serviceTest1() throws Exception {
		Deal deal = new Deal();
		DealIngestion dealIngestion = new DealIngestion();
		deal.setClientContact("9438227665");
		deal.setDealName("XXX");
		deal.setDealIngestion(dealIngestion);
		LoanData loan = new LoanData();
		loan.setLoanId("1");
		loan.setLoanData("xxx");
		File file = new File("D:\\Sample_Tape.xlsx");
		FileInputStream input = new FileInputStream(file);

		MultipartFile mulfile = new MockMultipartFile("file", file.getName(),
				"text/plain", IOUtils.toByteArray(input));
		
		String clientContact = "{\"dealName\":\"xxx\",\"clientContact\":\"22\"}.";

		Mockito.when(dealSetupRepository.save(deal)).thenReturn(deal);
		Mockito.when(loanRepository.save(loan)).thenReturn(loan);
		Mockito.when(loanRepository.findByLoanId("1")).thenReturn(loan);
		dealSetupService.createDealSetup(mulfile, clientContact);
		//assertEquals(loanRepository.save(loan).getLoanId(),loan.getLoanId());

	}

	@Test
	public void serviceTest2() throws MockResourceException {
		List<LoanData> loan = new ArrayList<LoanData>();
		LoanData loandata = new LoanData();
		loandata.setLoanId("1");
		loandata.setLoanData("xxx");
		loan.add(loandata);
		Mockito.when(loanRepository.findAll()).thenReturn(loan);
		dealSetupService.getAllDeals();
		assertEquals(loan.get(0).getLoanId(),dealSetupService.getAllDeals().get(0).getLoanId());

	}

	public void serviceTest3() {

	}
}
