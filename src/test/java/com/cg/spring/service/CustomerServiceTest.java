package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cg.spring.model.Customer;

@Transactional
@SpringBootTest
class CustomerServiceTest {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CustomerServiceTest.class);

	@Autowired
	ICustomerService custService;

	// Add
	@Test
	@Disabled
	void testaddCustomer() {
		Customer customer = new Customer(9, "Akshay", "ak780.$", 9320411200L, "ak07ay@gmail.com");
		Customer persistedCust = custService.addCustomer(customer);
		logger.info(persistedCust);
		assertEquals(9, persistedCust.getCustomerId());
		assertEquals("Akshay", persistedCust.getCustomerName());
		assertEquals("ak780.$", persistedCust.getCustomerPassword());
		assertEquals(9320411200L, persistedCust.getMobileNumber());
		assertEquals("ak07ay@gmail.com", persistedCust.getEmailId());
		//assertEquals(null, persistedCust.getCart());
	}

	// Update
	@Test
	@Disabled
	void testupdateCustomer() {
		Customer customer = new Customer(7, "Sahana", "saha*001", 9655241002L, "sahana22@yahoo.com");
		customer.setCustomerId(7);
		customer.setCustomerName("Sahana");
		customer.setCustomerPassword("sahana*001");
		customer.setEmailId("sahana22@yahoo.com");
		customer.setMobileNumber(9655241002L);

		Customer uptdcust = custService.updateCustomer(customer);
		logger.info(uptdcust);
		assertEquals("Sahana", uptdcust.getCustomerName());
	}

	// ViewById
	@Test
	@Disabled
	void testviewCustomerById() {
		Customer customer = custService.viewCustomerById(7);
		logger.info(customer);
		assertEquals(7, customer.getCustomerId());
		assertEquals("Aarthi", customer.getCustomerName());
		assertEquals("aaru0202", customer.getCustomerPassword());
	}

	// ViewAll
	@Test
	@Disabled
	void testshowallCustomers() {
		List<Customer> customers = custService.showAllCustomers();
		logger.info(customers);
		assertEquals(4, customers.size());
	}

	// Delete
	@Test
	@Disabled
	void testdeleteCustomer() {
		Customer customer = new Customer(5, "Akhil", "akhi8679", 9522045112L, "akhil86@gmail.com");
		Customer persistCust = custService.deleteCustomer(5);
		logger.info(persistCust);
		assertEquals(5, persistCust.getCustomerId());
		assertEquals("Akhil", persistCust.getCustomerName());
		assertEquals("akhi8679", persistCust.getCustomerPassword());
		assertEquals(9522045112L, persistCust.getMobileNumber());
		assertEquals("akhil86@gmail.com", persistCust.getEmailId());
	}
}
