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
		Optional<Customer> customer = Optional.ofNullable(registerRepo.findCustomerByEmail(user.getEmail()));
		Customer cust = null;
		if(customer.isPresent()) {
			Optional<CustomerLogin> dbUsr = loginRepo.findById(user.getEmail());

			if ( !dbUsr.isPresent() || !dbUsr.get().isLoggedIn()) {
				user.setLoggedIn(true);
				loginRepo.save(user);
				cust=registerRepo.findCustomerByEmail(user.getEmail());
				return cust;
			} 
		}
		

		return cust;
	}

	@Override
	public String logout(String email) {
		logger.info("Customer logout");
		Optional<CustomerLogin> userfield = loginRepo.findById(email);
		logger.info(userfield.get());
		CustomerLogin dbUsr = null;
		if (userfield.isPresent()) {
			dbUsr = userfield.get();
		}
		if (dbUsr != null && dbUsr.getEmail().equals(email) && dbUsr.isLoggedIn()) {

			dbUsr.setLoggedIn(false);
			loginRepo.save(dbUsr);
			return "logged out";
		}
		throw new CustomerNotFoundException("User not logged in");
	}

	public Customer getUser(String email) {
		Optional<CustomerLogin> userfield = loginRepo.findById(email);
		logger.info(userfield.get());
		CustomerLogin dbUsr = null;
		if (userfield.isPresent()) {
			dbUsr = userfield.get();
		}
		if (dbUsr != null && dbUsr.getEmail().equals(email) && dbUsr.isLoggedIn()) {
			Optional<Customer> customer = Optional.ofNullable(registerRepo.findCustomerByEmail(email));
			Customer cust=customer.get();
			Customer obj=new Customer();
			obj.setCustomerId(cust.getCustomerId());
			obj.setAddress(cust.getAddress());
			obj.setCustomerName(cust.getCustomerName());
			obj.setEmail(cust.getEmail());
			obj.setMobileNumber(cust.getMobileNumber());
			
			return obj;
		}
		
		return null;
		
	}

}
