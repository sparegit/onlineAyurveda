package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Cart;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;
import com.cg.spring.repository.ICartRepository;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	ICustomerRepository custRepo;
	@Autowired
	IOrderRepository orderRepo;
	
	@Autowired
	ICartService cartServ;
	
	@Autowired
	ICartRepository cartRepo;
	
	// Used to store the given customer Object passed from the controller
	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("Adding customer to the database");
		Optional<Customer> opt = Optional.ofNullable(custRepo.findCustomerByEmail(customer.getEmail()));
		if(opt.isPresent()) {
			return null;
		}
		Customer cust=custRepo.save(customer);
		int id= cust.getCustomerId();
		Cart cart=cartServ.addCartToCustomer(id);
		cartRepo.save(cart);
		return cust;
	}
	// Used to update the customer of given object
	@Override
	public Customer updateCustomer(Customer customer) {
		logger.info("Update customer details to the database");
		Optional<Customer> opt = custRepo.findById(customer.getCustomerId());
		if (!opt.isPresent()) {
			return null;
		}
		Customer cust = opt.get();
		cust.setCustomerName(customer.getCustomerName());
		cust.setCustomerPassword(customer.getCustomerPassword());
		cust.setEmail(customer.getEmail());
		cust.setMobileNumber(customer.getMobileNumber());
		return custRepo.save(cust);
	}
	// Used to update the customer name of given object
	@Override
	public Customer updateCustomerName(Customer customer) {
		logger.info("Updating customer name");
		Optional<Customer> opt = custRepo.findById(customer.getCustomerId());
		if (!opt.isPresent()) {
			return null;
		}
		Customer cust = opt.get();
		cust.setCustomerName(customer.getCustomerName());
		return custRepo.save(cust);
	}
	// Get a specific customer of the given ID
	@Override
	public Customer viewCustomerById(int customerid) {
		logger.info("View customer by id");
		Optional<Customer> opt = custRepo.findById(customerid);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	// Used to list all the customers from the database
	@Override
	public List<Customer> showAllCustomers() {
		logger.info("View all customers");
		return custRepo.findAll();
	}
	// Used to delete the customer
	@Override
	public Customer deleteCustomer(int customerid) {
		logger.info("Delete customer by id");
		Optional<Customer> opt = custRepo.findById(customerid);
		if (!opt.isPresent()) {
			return null;
		}
		Customer cust = opt.get();
		custRepo.deleteById(customerid);
		return cust;
	}
	// Used to list all the orders from the database
	@Override
	public List<Order> showAllOrders() {
		logger.info("View all orders");
		return orderRepo.findAll();
	}
	// Get a specific customer of the given email id
	@Override
	public Customer findCustomerByEmail(String email) {
		return custRepo.findCustomerByEmail(email);
	}
}
