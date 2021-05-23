package com.cg.spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.spring.model.Order;

public class OrderRepositoryTest {
	@Autowired
	IOrderRepository orderRepository;
	
	@Test
	@Disabled
	void testCreateOrder() {
		Order order= new Order(121,"2019-03-29",null, "2020-03-29",200f, "pass",null);
		
		Order persistedOrder = orderRepository.save(order);
		
		assertEquals(121,persistedOrder.getOrderId());
		assertEquals("2020-03-29",persistedOrder.getDispatchDate());
		assertEquals("2019-03-29",persistedOrder.getOrderDate());
		assertEquals("pass",persistedOrder.getStatus());
		assertEquals("200f",persistedOrder.getTotalCost());
	}
}
