package com.tavant.mockdrill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tavant.mockdrill.exception.MockInternalException;
import com.tavant.mockdrill.exception.MockResourceException;
import com.tavant.mockdrill.logger.MockLogger;
import com.tavant.mockdrill.model.LoanDetail;
import com.tavant.mockdrill.service.DealSetupService;

@CrossOrigin(origins = "*",maxAge=3600)
@Controller
@RequestMapping("/dealsetup")
public class DealSetUp {

	@Autowired
	private DealSetupService dealSetupService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = { "application/json", "application/xml" })
	//@PostMapping("/create" )
	public ResponseEntity<String> createDealSetup(@RequestParam("file") MultipartFile file,
			@RequestParam("desc") String desc) {
		 MockLogger.entry(MockLogger.getMethodName(), MockLogger.getClassName());
		 System.out.println("inside");
		try {
			boolean LoadStatus = dealSetupService.createDealSetup(file,desc);
		}catch(MockInternalException e){ 
			e.handleException(e, e.getMessage());
		}catch (MockResourceException e) {
			e.handleException(e, e.getMessage());
		}
		MockLogger.exit(MockLogger.getMethodName(), MockLogger.getClassName());
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping("/display")
	public ResponseEntity<?> getAllDeals() {
		List<LoanDetail> loans = new ArrayList<LoanDetail>();
		try{
			loans = dealSetupService.getAllDeals();
		}catch(MockResourceException e){
			e.handleException(e, e.getMessage());
		}
		return new ResponseEntity<List<LoanDetail>>(loans, HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<String> getLoanDetails() {

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}
