package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	ICustomerRepository custRepo;
	@Autowired
	IOrderRepository orderRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("Adding customer to the database");
		return custRepo.save(customer);
	}

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
		cust.setEmailId(customer.getEmailId());
		cust.setMobileNumber(customer.getMobileNumber());
		return custRepo.save(cust);
	}

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

	@Override
	public Customer viewCustomerById(int customerid) {
		logger.info("View customer by id");
		Optional<Customer> opt = custRepo.findById(customerid);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public List<Customer> showAllCustomers() {
		logger.info("View all customers");
		return custRepo.findAll();
	}

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

	@Override
	public List<Order> showAllOrders() {
		logger.info("View all orders");
		return orderRepo.findAll();
	}
	
	@Override
	public Customer findCustomerByEmailId(String email) {
		return custRepo.findCustomerByEmailId(email);
	}
}
