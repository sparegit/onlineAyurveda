package com.cg.spring.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.exception.CustomerNotFoundException;
import com.cg.spring.model.Customer;
import com.cg.spring.model.CustomerLogin;
import com.cg.spring.repository.ICustomerLoginRepository;
import com.cg.spring.repository.ICustomerRepository;
@Service
public class CustomerLoginServiceImplementation implements ICustomerLoginService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerLoginServiceImplementation.class);
	
	@Autowired
	ICustomerLoginRepository loginRepo;
	@Autowired
	ICustomerRepository registerRepo;
	
	@Override
	public Customer login(CustomerLogin user) {
		logger.info("Customer login");
		Optional<CustomerLogin> dbUsr = loginRepo.findById(user.getEmail());
		Customer cust = null;
		if ( !dbUsr.isPresent() || !dbUsr.get().isLoggedIn()) {
			user.setLoggedIn(true);
			loginRepo.save(user);
			cust=registerRepo.findCustomerByEmail(user.getEmail());
			return cust;
		} 

		return cust;
	}

	@Override
	public String logout(String userId) {
		logger.info("Customer logout");
		Optional<CustomerLogin> userfield = loginRepo.findById(userId);
		CustomerLogin dbUsr = null;
		if (userfield.isPresent()) {
			dbUsr = userfield.get();
		}
		if (dbUsr != null && dbUsr.getEmail().equals(userId) && dbUsr.isLoggedIn()) {

			dbUsr.setLoggedIn(false);
			loginRepo.save(dbUsr);
			return "logged out";
		}
		throw new CustomerNotFoundException("User not logged in");
	}

	

}
