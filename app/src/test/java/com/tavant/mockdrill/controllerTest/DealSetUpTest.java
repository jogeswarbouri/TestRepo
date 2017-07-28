package com.tavant.mockdrill.controllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tavant.mockdrill.WebConfig;
import com.tavant.mockdrill.controller.DealSetUp;
import com.tavant.mockdrill.model.LoanDetail;
import com.tavant.mockdrill.service.DealSetupService;

@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(DealSetUp.class)
public class DealSetUpTest {
	
	 private MockMvc mockMvc;
	 
	 @Mock
	 private DealSetupService dealSetupService;
	 
	 @InjectMocks
	 private DealSetUp controllerToTest;
	 
	 @Autowired
	 private WebApplicationContext webApplicationContext;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(controllerToTest)
	                .build();
	    }
	    
	   // @Test
		public void create() throws Exception {
	    	File initialFile = new File("D:\\Sample_Tape.xlsx");
	    	byte[] file = new byte[(int) initialFile.length()];
	    	 final MockMultipartFile multipartFile = new MockMultipartFile("aMultiPartFile.txt", new FileInputStream(initialFile));

	    	
	    	//Mockito.doNothing().when(dealSetupService.createDealSetup(Mockito.any(), Mockito.anyString()));
	    	 Mockito.when(dealSetupService.createDealSetup(Mockito.any(), Mockito.anyString())).thenReturn(true);
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/dealsetup/create")
					.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
					.accept(MediaType.APPLICATION_JSON)
					 .requestAttr("file", multipartFile)
                     .requestAttr("desc", "This is the string");
					/*.content(file)
					.content((desc))*/

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());

		}
		
		@Test
	      public void display() throws Exception {
	            
	            
	        List<LoanDetail> users = Arrays.asList(
	                new LoanDetail("1", "result in json string","10"),
	                new LoanDetail("2", "result in json string","10"));
	            when(dealSetupService.getAllDeals()).thenReturn(users);
	        mockMvc.perform(get("/dealsetup/display").accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].loanId", is("1")))
	        .andExpect(jsonPath("$[0].result", is("result in json string")))
	        .andExpect(jsonPath("$[0].count", is("10")))
	        .andExpect(jsonPath("$[1].loanId", is("2")))
	        .andExpect(jsonPath("$[1].result", is("result in json string")))
	        .andExpect(jsonPath("$[1].count", is("10")));
	        verify(dealSetupService).getAllDeals();
	    }

	    
	   @Test
	    public void test_get_all_success() throws Exception {
	       
	    	List<LoanDetail> users = Arrays.asList(
	                new LoanDetail("1", "result in json string","10"),
	                new LoanDetail("2", "result in json string","10"));
	        
	        Mockito.when(dealSetupService.getAllDeals()).thenReturn(users);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dealsetup/display").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals("Success",200,result.getResponse().getStatus());
			
			
	    }
	    

}