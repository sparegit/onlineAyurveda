package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public Customer updateCustomerName(Customer customer);

	public Customer viewCustomerById(int customerid);

	public List<Customer> showAllCustomers();

	public Customer deleteCustomer(int customerid);

	public List<Order> showAllOrders();
	
	public Customer findCustomerByEmailId(String email);
}
