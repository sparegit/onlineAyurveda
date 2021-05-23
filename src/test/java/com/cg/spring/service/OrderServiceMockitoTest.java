package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

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

import com.cg.spring.model.Order;
import com.cg.spring.repository.IOrderRepository;

@ExtendWith(SpringExtension.class)
class OrderServiceMockitoTest {
	@InjectMocks
	OrderServiceImplementation orderService;

	@MockBean
	IOrderRepository orderRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Disabled
	void testCreateOrder() {
		Order order = new Order(9, "2019-03-29", null, "2020-03-29", 200f, "pass",null);

		Mockito.when(orderRepository.save(order)).thenReturn(order);

		Order persistedOrder = orderService.save(order);

		assertEquals(9, persistedOrder.getOrderId());
		assertEquals("2020-03-29", persistedOrder.getDispatchDate());
		assertEquals("2019-03-29", persistedOrder.getOrderDate());
		assertEquals("pass", persistedOrder.getStatus());
		assertEquals(200f, persistedOrder.getTotalCost());

	}

	@Test

	void testshowAllOrders() {
		Order order0 = new Order(10, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		Order order1 = new Order(11, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		Order order2 = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order0);
		orderList.add(order1);
		orderList.add(order2);

		Mockito.when(orderRepository.findAll()).thenReturn(orderList);
		List<Order> orders = orderService.findAll();
		assertEquals(3, orders.size());

	}

	@Test

	void testupdateOrder() {
		Order order0 = new Order(10, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		Mockito.when(orderRepository.findById(10)).thenReturn(Optional.of(order0));
		Mockito.when(orderRepository.save(order0)).thenReturn(order0);

		Order persistedOrder = orderService.update(order0);
		assertEquals(10, persistedOrder.getOrderId());
		assertEquals("fail", persistedOrder.getStatus());

	}

	@Test
	void testviewOrderById() {
		Order ord = new Order(9, "2019-03-29", null, "2020-03-29", 200f ,"fail",null);
		orderService.save(ord);

		Mockito.when(orderRepository.findById(9)).thenReturn(Optional.of(ord));

		Order persistedOrd = orderService.findById(9);

		assertEquals(9, persistedOrd.getOrderId());
		assertEquals("fail", persistedOrd.getStatus());
		assertEquals("2019-03-29", persistedOrd.getOrderDate());
	}

	@Test
	@Disabled
	void testdeleteOrder() {
		Order order0 = new Order(10, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		Mockito.when(orderRepository.findById(10)).thenReturn(Optional.of(order0));
		orderRepository.deleteById(11);
		Order persistedOrder = orderService.cancelOrder(11);
		assertEquals(10, persistedOrder.getOrderId());
		assertEquals("fail", persistedOrder.getStatus());
	}

}
