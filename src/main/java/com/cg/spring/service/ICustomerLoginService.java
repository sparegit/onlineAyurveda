package com.cg.spring.service;


import com.cg.spring.model.Customer;
import com.cg.spring.model.CustomerLogin;

public interface ICustomerLoginService {
public Customer login(CustomerLogin user);

public	String logout(String userId);
}
