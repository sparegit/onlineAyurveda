package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.repository.IMedicineRepository;

@SpringBootTest
class OrderServiceTest {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(OrderServiceTest.class);

	@Autowired
	IOrderService orderService;
	@Autowired
	IMedicineService medServ;
	@Autowired
	IMedicineRepository medRepo;
	// Testing whether the order database has orders or null.
	@Test

	void testFindAllOrders() {
		List<Order> orders = orderService.findAll();
		logger.info(orders);
		assertEquals(5, orders.size());
	}
	// Testing whether the given id fetches the given order or not.

	@Test
	void testFindOrderById() {
		Order order = orderService.findById(9);
		logger.info(order);
		assertEquals(9, order.getOrderId());
		assertEquals("pass", order.getStatus());
		assertEquals("2019-03-29", order.getOrderDate());
		assertEquals("2020-03-29", order.getDispatchDate());
		assertEquals(200f, order.getTotalCost());
	}
	// Testing whether the given date fetches the given order or not.
	@Test
	void testFindByOrderDate() {
		List<Order> orders = orderService.findAllOrderByOrderDate("2020-03-29");
		logger.info(orders);
		assertEquals(0, orders.size());
	}
	// Testing whether the order gets added to the database.
	@Test
	@Disabled
	void testAddOrder() {
		Order order = new Order(9, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		Order persistedOrder = orderService.save(order);
		logger.info(persistedOrder);
		assertEquals(9, persistedOrder.getOrderId());
		assertEquals("2019-03-29", persistedOrder.getOrderDate());

	}
	// Testing whether the address gets cancelled from the database.
	@Test
	void testCancelOrder() {
		Order order0 = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		orderService.save(order0);
		Order persistedOrder = orderService.cancelOrder(12);
		logger.info(persistedOrder);
		assertEquals(12, persistedOrder.getOrderId());
		assertEquals("fail", persistedOrder.getStatus());

	}
	// Testing whether the given medicine fetches the order or not.
	@Test
	void testGetOrderListBasedOnMedicineId() {
		Medicine med = new Medicine(144, "asparagus", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		List<Medicine> medList = new ArrayList<>();
		medList.add(med);
		Order order = new Order(4, "2019-03-29", medList, "2020-03-29", 200f, "pass",null);
		orderService.save(order);
		List<Order> orders = orderService.findAll();
		List<Order> ordersWithMedId = new ArrayList<Order>();
		logger.info(ordersWithMedId);
		for (int i = 0; i < orders.size(); i++) {
			List<Medicine> medicines = orders.get(i).getMedicineList();
			for (int j = 0; j < medicines.size(); j++) {
				if (medicines.get(j).getMedicineId() == 144) {
					ordersWithMedId.add(orders.get(i));
				}
			}
		}
		assertEquals(0, ordersWithMedId.size());

	}
	// Testing the total calculated cost
	@Test
	void testCalculateTotalCost() {
		Order order0 = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		orderService.save(order0);
		Order ord= orderService.findById(12);
		logger.info(ord);
		assertEquals(200f, ord.getTotalCost());
	}
	// Testing whether the order gets updated to the database.
	@Test
	void testUpdateOrderStatusByUserId() {
		Order ord = orderService.findById(12);
		Order order = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		ord.setStatus(order.getStatus());
		logger.info(order);
		assertEquals("pass", ord.getStatus());
	}

}
