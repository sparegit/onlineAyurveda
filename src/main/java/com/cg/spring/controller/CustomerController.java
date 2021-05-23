package com.cg.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.CustomerNotFoundException;
import com.cg.spring.model.Customer;

import com.cg.spring.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	ICustomerService custService;

	// Add
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		custService.addCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Update
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
		Customer cust = custService.updateCustomer(customer);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to update " + id);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	// UpdateName
	@PatchMapping("/customer/{name}")
	public ResponseEntity<Customer> updateCustomerName(@PathVariable("name") String customerName,
			@RequestBody Customer customer) {
		return new ResponseEntity<>(custService.updateCustomerName(customer), HttpStatus.OK);
	}

	// ViewbyId
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable("id") int customerId) {
		Customer cust = custService.viewCustomerById(customerId);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to view " + customerId);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	// ViewAll
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> showAllCustomers() {
		return new ResponseEntity<>(custService.showAllCustomers(), HttpStatus.OK);
	}

	// Delete
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int customerId) {
		Customer cust = custService.viewCustomerById(customerId);
		if (cust == null) {
			throw new CustomerNotFoundException("Customer not found with id to delete " + customerId);
		}
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
}
