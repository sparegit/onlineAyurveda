package com.cg.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.spring.exception.CustomerNotFoundException;
import com.cg.spring.model.Customer;
import com.cg.spring.service.ICustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerController.class);
	// We are autowiring the customer service layer to this controller layer of
	// customer
	@Autowired
	ICustomerService custService;
	
	// This controller is used to create a new or add new customer and redirects it
	// to the service layer
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		logger.info("Adding customer in database");
		Customer cust=custService.findCustomerByEmail(customer.getEmail());
		if(cust == null) {
			custService.addCustomer(customer);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		    throw new CustomerNotFoundException("already registered");	
	}

	// This controller is used to update a new or add new customer and redirects it
		// to the service layer
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@Valid @RequestBody Customer customer) {
		logger.info("Updating customer details in database");
		Customer cust = custService.updateCustomer(customer);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to update " + id);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	// This function is used to update a specific customer on basis of given
		// customer name
	@PatchMapping("/customer/{name}")
	public ResponseEntity<Customer> updateCustomerName(@PathVariable("name") String customerName,
		@Valid	@RequestBody Customer customer) {
		logger.info("Updating customer name in database");
		return new ResponseEntity<>(custService.updateCustomerName(customer), HttpStatus.OK);
	}

	// This controller is used to get a specific customer on basis of ID
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("id") int customerId) {
		logger.info("View customer by Id");
		Customer cust = custService.viewCustomerById(customerId);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to view " + customerId);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	// This controller is used to return and list all the customers found in the
	// database and request to the service to perform the action
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> showAllCustomers() {
		logger.info("View all customers");
		return new ResponseEntity<>(custService.showAllCustomers(), HttpStatus.OK);
	}

	// this controller function perform deletion of a specific given customer
	// and request the service to perform the action and returns the message as
	// deleted else throw exception
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int customerId) {
		logger.info("Delete customer by id");
		Customer cust = custService.viewCustomerById(customerId);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to delete " + customerId);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
}
