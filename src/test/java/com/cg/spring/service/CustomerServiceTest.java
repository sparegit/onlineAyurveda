package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cg.spring.model.Customer;

@Transactional
@SpringBootTest
class CustomerServiceTest {

	@Autowired
	ICustomerService custService;

	@Test
	@Disabled
	void testaddCustomer() {
		Customer customer = new Customer(9, "Akshay", "ak780.$", 9320411200L, "ak07ay@gmail.com", null);
		Customer persistedCust = custService.addCustomer(customer);
		assertEquals(9, persistedCust.getCustomerId());
		assertEquals("Akshay", persistedCust.getCustomerName());
		assertEquals("ak780.$", persistedCust.getCustomerPassword());
		assertEquals(9320411200L, persistedCust.getMobileNumber());
		assertEquals("ak07ay@gmail.com", persistedCust.getEmailId());
		assertEquals(null, persistedCust.getCart());
	}

	@Test
	@Disabled
	void testupdateCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(7);
		customer.setCustomerName("Sahana");
		customer.setCustomerPassword("saha*001");
		customer.setEmailId("sahana22@yahoo.com");
		customer.setMobileNumber(9655241002L);

		Customer uptdcust = custService.updateCustomer(customer);
		assertEquals("Sahana", uptdcust.getCustomerName());
	}

	@Test
	@Disabled
	void testviewCustomerById() {
		Customer customer = custService.viewCustomerById(7);
		assertEquals(7, customer.getCustomerId());
		assertEquals("Aarthi", customer.getCustomerName());
		assertEquals("aaru0202", customer.getCustomerPassword());
	}

	@Test
	@Disabled
	void testshowallCustomers() {
		List<Customer> customers = custService.showAllCustomers();
		assertEquals(4, customers.size());
	}

	@Test
	@Disabled
	void testdeleteCustomer() {
		Customer customer = new Customer(5, "Akhil", "akhi8679", 9522045112L, "akhil86@gmail.com", null);
		Customer persistCust = custService.deleteCustomer(5);

		assertEquals(5, persistCust.getCustomerId());
		assertEquals("Akhil", persistCust.getCustomerName());
		assertEquals("akhi8679", persistCust.getCustomerPassword());
		assertEquals(9522045112L, persistCust.getMobileNumber());
		assertEquals("akhil86@gmail.com", persistCust.getEmailId());
	}
}
