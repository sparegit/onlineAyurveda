package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;

public interface ICustomerService {

	// Method to be override by the implementing class
		public Customer addCustomer(Customer customer);

		// Method to be override by the implementing class
		public Customer updateCustomer(Customer customer);

		// Method to be override by the implementing class
		public Customer updateCustomerName(Customer customer);

		// Method to be override by the implementing class
		public Customer viewCustomerById(int customerid);

		// Method to be override by the implementing class
		public List<Customer> showAllCustomers();

		// Method to be override by the implementing class
		public Customer deleteCustomer(int customerid);

		// Method to be override by the implementing class
		public List<Order> showAllOrders();

		// Method to be override by the implementing class
		public Customer findCustomerByEmail(String email);
}
