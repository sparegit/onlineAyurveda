package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.repository.IMedicineRepository;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	IOrderService orderService;
	@Autowired
	IMedicineService medServ;
	@Autowired
	IMedicineRepository medRepo;

	@Test

	void testFindAllOrders() {
		List<Order> orders = orderService.findAll();
		assertEquals(5, orders.size());
	}

	@Test
	void testFindOrderById() {
		Order order = orderService.findById(9);
		assertEquals(9, order.getOrderId());
		assertEquals("pass", order.getStatus());
		assertEquals("2019-03-29", order.getOrderDate());
		assertEquals("2020-03-29", order.getDispatchDate());
		assertEquals(200f, order.getTotalCost());
	}

	@Test
	void testFindByOrderDate() {
		List<Order> orders = orderService.findAllOrderByOrderDate("2020-03-29");
		assertEquals(0, orders.size());
	}

	@Test
	@Disabled
	void testAddOrder() {
		Order order = new Order(9, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		Order persistedOrder = orderService.save(order);
		assertEquals(9, persistedOrder.getOrderId());
		assertEquals("2019-03-29", persistedOrder.getOrderDate());

	}

	@Test
	void testCancelOrder() {
		Order order0 = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		orderService.save(order0);
		Order persistedOrder = orderService.cancelOrder(12);
		assertEquals(12, persistedOrder.getOrderId());
		assertEquals("fail", persistedOrder.getStatus());

	}

	@Test
	void testGetOrderListBasedOnMedicineId() {
		Medicine med = new Medicine("144", "asparagus", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		List<Medicine> medList = new ArrayList<>();
		medList.add(med);
		Order order = new Order(4, "2019-03-29", medList, "2020-03-29", 200f, "pass",null);
		orderService.save(order);
		List<Order> orders = orderService.findAll();
		List<Order> ordersWithMedId = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			List<Medicine> medicines = orders.get(i).getMedicineList();
			for (int j = 0; j < medicines.size(); j++) {
				if (medicines.get(j).getMedicineId() == "144") {
					ordersWithMedId.add(orders.get(i));
				}
			}
		}
		assertEquals(0, ordersWithMedId.size());

	}
	@Test
	void testCalculateTotalCost() {
		Order order0 = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "fail",null);
		orderService.save(order0);
		Order ord= orderService.findById(12);
		assertEquals(200f, ord.getTotalCost());
	}
	@Test
	void testUpdateOrderStatusByUserId() {
		Order ord = orderService.findById(12);
		Order order = new Order(12, "2019-03-29", null, "2020-03-29", 200f, "pass",null);
		ord.setStatus(order.getStatus());
		assertEquals("pass", ord.getStatus());
	}

}
