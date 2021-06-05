package com.cg.spring.service;


import com.cg.spring.model.Customer;
import com.cg.spring.model.CustomerLogin;

public interface ICustomerLoginService {
	// Method to be override by the implementing class
	public Customer login(CustomerLogin user);

	// Method to be override by the implementing class
	public String logout(String email);
	
	public Customer getUser(String email);
}
