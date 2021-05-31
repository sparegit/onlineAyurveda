package com.cg.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.exception.CustomerNotFoundException;
import com.cg.spring.model.Customer;
import com.cg.spring.model.CustomerLogin;
import com.cg.spring.repository.ICustomerLoginRepository;
import com.cg.spring.repository.ICustomerRepository;
@Service
public class CustomerLoginServiceImplementation implements ICustomerLoginService {
	
	@Autowired
	ICustomerLoginRepository loginRepo;
	@Autowired
	ICustomerRepository registerRepo;

	@Override
	public Customer login(CustomerLogin user) {
		Optional<CustomerLogin> dbUsr = loginRepo.findById(user.getEmail());
		Customer cust = null;
		if ( !dbUsr.isPresent() || !dbUsr.get().isLoggedIn()) {
			user.setLoggedIn(true);
			loginRepo.save(user);
			cust=registerRepo.findCustomerByEmailId(user.getEmail());
			return cust;
		} 

		return cust;
	}

	@Override
	public String logout(String userId) {
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
