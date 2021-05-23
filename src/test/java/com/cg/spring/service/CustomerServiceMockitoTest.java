package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.spring.model.Address;
import com.cg.spring.model.Customer;
import com.cg.spring.repository.ICustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {

	@InjectMocks
	CustomerServiceImpl custService;

	@MockBean
	ICustomerRepository custRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Disabled
	void testaddCustomer() {
		Customer customer = new Customer(10, "Bharath", "br230899*", 9200145220L, "bharath88@gmail.com", null);

		Mockito.when(custRepo.save(customer)).thenReturn(customer);

		Customer persistedCust = custService.addCustomer(customer);

		assertEquals(10, persistedCust.getCustomerId());
		assertEquals("Bharath", persistedCust.getCustomerName());
		assertEquals("br230899*", persistedCust.getCustomerPassword());
		assertEquals("bharath88@gmail.com", persistedCust.getEmailId());
		assertEquals(9200145220L, persistedCust.getMobileNumber());
	}

	@Test
	@Disabled
	void testshowAllCustomers() {
		Address addr = new Address();
		Customer customer1 = new Customer(11, "Shilpa", "shilpa454+", 8655296220L, "shipaa33@yahoo.com", null);
		Customer customer2 = new Customer(12, "Sara", "s@ra009", 9665210222L, "sara12@yahoo.com", null);
		Customer customer3 = new Customer(13, "Siri", "siri4*", 8033278551L, "siriap8@yahoo.com", null);
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);

		Mockito.when(custRepo.findAll()).thenReturn(customerList);
		List<Customer> customers = custService.showAllCustomers();
		assertEquals(3, customers.size());

	}

	@Test
	@Disabled
	void testupdateCustomer() {
		Customer customer = new Customer(12, "Sara", "sara433*", 9665210222L, "sara123@gmail.com", null);
		Mockito.when(custRepo.findById(12)).thenReturn(Optional.of(customer));
		Mockito.when(custRepo.save(customer)).thenReturn(customer);

		Customer persistedCust = custService.updateCustomer(customer);
		assertEquals(12, persistedCust.getCustomerId());
		assertEquals("Sara", persistedCust.getCustomerName());
		assertEquals("sara433*", persistedCust.getCustomerPassword());
		assertEquals("sara123@gmail.com", persistedCust.getEmailId());
		assertEquals(9665210222L, persistedCust.getMobileNumber());
	}

	@Test
	@Disabled
	void testviewCustomerById() {
		Customer customer = new Customer(13, "Siri", "siri4*", 8033278551L, "siriap8@yahoo.com", null);
		Mockito.when(custRepo.findById(13)).thenReturn(Optional.of(customer));
		Customer persistedCust = custService.viewCustomerById(13);

		assertEquals(13, persistedCust.getCustomerId());
		assertEquals("Siri", persistedCust.getCustomerName());
		assertEquals("siri4*", persistedCust.getCustomerPassword());
		assertEquals(8033278551L, persistedCust.getMobileNumber());
		assertEquals("siriap8@yahoo.com", persistedCust.getEmailId());
	}

	@Test
	@Disabled
	void testdeleteCustomer() {
		Customer customer = new Customer(11, "Shilpa", "shilpa454+", 8655296220L, "shipaa33@yahoo.com", null);
		Mockito.when(custRepo.findById(11)).thenReturn(Optional.of(customer));
		custRepo.deleteById(11);
		Customer persistedCust = custService.deleteCustomer(11);

		assertEquals(11, persistedCust.getCustomerId());
		assertEquals("Shilpa", persistedCust.getCustomerName());
		assertEquals("shilpa454+", persistedCust.getCustomerPassword());
		assertEquals(8655296220L, persistedCust.getMobileNumber());
		assertEquals("shipaa33@yahoo.com", persistedCust.getEmailId());

	}
}