package com.cg.spring.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.CustomerNotFoundException;
import com.cg.spring.model.Customer;
import com.cg.spring.model.CustomerLogin;
import com.cg.spring.service.ICustomerLoginService;
import com.cg.spring.service.ICustomerService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerLoginController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerLoginController.class);
	
	@Autowired
	ICustomerService custService;
	@Autowired
	ICustomerLoginService loginService;
	@PostMapping("/customer/login")
	//login service
	public ResponseEntity<Customer>  Login(@Valid @RequestBody CustomerLogin loginentity) {
		logger.info("Login by the customer");
		Customer cust=null;
		if (loginentity.getEmail()==null || loginentity.getPassword()==null || loginentity.getEmail().equals("")||loginentity.getPassword().equals("")) {
			throw new CustomerNotFoundException("Userid or Password is invalid");
		}	
		Customer userfield = custService.findCustomerByEmail(loginentity.getEmail());
		if(userfield !=null && userfield.getCustomerPassword().equals(loginentity.getPassword())) {
			cust = loginService.login(loginentity);
		}
		else if(userfield!=null){
			throw new CustomerNotFoundException("Userid or Password is invalid");
		}
		else  {
			throw new CustomerNotFoundException("User Not Registered");
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	 //logout service
		@GetMapping("customer/logout/{emailId}")
		public String Logout(@Email @PathVariable("emailId")String emailId){
			logger.info("Logout by the customer");
			return loginService.logout(emailId);
		}
}
