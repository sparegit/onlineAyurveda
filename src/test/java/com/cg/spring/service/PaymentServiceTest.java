package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Payment;




@SpringBootTest
class PaymentServiceTest {
	
	@Autowired
	IPaymentService iservice;
	
	@Test
	//@Disabled
	public void testsavePaymentDetails() {
		Payment payById = iservice.findById(4);
		payById.setPaymentType("COD");
		payById.setShippingFee(30.00);
		payById.setTotalPrice(130.00);
		Payment payupdate = iservice.save(payById);
		
		assertEquals("COD",payupdate.getPaymentType());
		assertEquals(30.00, payupdate.getShippingFee());
		assertEquals(130.00, payupdate.getTotalPrice());
	}
	
	@Test
	//@Disabled
	public void testfindPaymentById() {
		Payment findById = iservice.findById(2);
		assertEquals(2.00,findById.getItemTotal());
		assertEquals(100.00, findById.getTotalPrice());
	
	}
}