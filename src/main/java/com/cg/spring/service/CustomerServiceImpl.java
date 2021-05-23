package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository custRepo;
	@Autowired
	IOrderRepository orderRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {

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
		Optional<Customer> opt = custRepo.findById(customerid);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public List<Customer> showAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Customer deleteCustomer(int customerid) {
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
		return orderRepo.findAll();
	}

}
