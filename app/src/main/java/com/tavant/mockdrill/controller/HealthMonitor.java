package com.tavant.mockdrill.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HealthMonitor {
	
	@RequestMapping("/dealsetup/index")
	String home(ModelMap modal) {
		modal.addAttribute("title","Mock Drill");
		return "forward:/index";
	}
	
	@RequestMapping(value="/status", method=RequestMethod.GET)
	public ResponseEntity<String> status(){
		return new ResponseEntity<String>("Success", HttpStatus.OK);
		
	}

}
