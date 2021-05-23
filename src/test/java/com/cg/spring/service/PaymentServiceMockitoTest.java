package com.cg.spring.service;

	import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.spring.model.Payment;
import com.cg.spring.repository.IPaymentRepository;

@SpringBootTest
class PaymentServiceMockitoTest {

	@InjectMocks
	PaymentServiceImplementation Paymentserviceimpl;
	
	@MockBean
	IPaymentRepository iordrep;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	//@Disabled
	public void testfindPaymentById() {
		Payment payId = new Payment(100,"upi",65f,34f,33f,null);
		
		Mockito.when(iordrep.findById(100)).thenReturn(Optional.of(payId));
		
		Payment pay = Paymentserviceimpl.findById(23);
		
		assertEquals(23,pay.getPaymentId());
		assertEquals(2.00, pay.getItemTotal());
	
	}
	
	@Test
	//@Disabled
	public void testsavePaymentDetails() {
		Payment payById = new Payment(100,"upi",65f,34f,33f,null);
		
		Mockito.when(iordrep.findById(26)).thenReturn(Optional.of(payById));
		Mockito.when(iordrep.save(payById)).thenReturn(payById);
		
		Payment payupdate = Paymentserviceimpl.save(payById);
		assertEquals(26,payupdate.getPaymentId());
		assertEquals(2.00, payupdate.getItemTotal());
	}
	
	 

}