package com.cg.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.OrderNotFoundException;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;
import com.cg.spring.service.IOrderService;

@RestController
public class OrderController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(OrderController.class);
	
	@Autowired
	IOrderService orderService;
	

	@GetMapping("/order/{id}")
	public ResponseEntity<List<Order>> findOrderById(@PathVariable("id") int ordId) {
		logger.info("View order by id");
		Order ord = orderService.findById(ordId);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to find:" + ordId);
		}
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> findAll() {
		logger.info("View all orders");
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
	}

	// WRITE
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		logger.info("Add order in database");
		return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
	}

	@PostMapping("/order/cancel/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("id") int id) {
		logger.info("Cancel order by id");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to cancel:" + id);
		}
		return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
	}

	@PutMapping("/order/update/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
		logger.info("Update order in the database");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to update:" + id);
		}
		return new ResponseEntity<>(orderService.update(ord), HttpStatus.OK);
	}

	@PatchMapping("/order/update/{id}")
	public ResponseEntity<Order> updateStatusByUserId(@PathVariable("id") int id, @RequestBody Order order) {
		logger.info("Update order status by userid");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to update status:" + id);
		}
		return new ResponseEntity<>(orderService.updateOrderStatusByUserId(id, order), HttpStatus.OK);
	}

	@GetMapping("/orders/date/{orderDate}")
	public ResponseEntity<List<Order>> findAllOrderByDate(@PathVariable("orderDate") String orderDate) {
		logger.info("Get all orders by date");
		return new ResponseEntity<>(orderService.findAllOrderByOrderDate(orderDate), HttpStatus.OK);
	}

	@GetMapping("/orders/list/cust/{customer}")
	public ResponseEntity<List<Order>> getOrderListBasedOnCustomer(@RequestBody Customer customer) {
		logger.info("View all orders based on customers");
		List<Order> ord = orderService.getOrderListBasedOnCustomer(customer);
		String name = customer.getCustomerName();
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with customer name:" + name);
		}
		return new ResponseEntity<>(orderService.getOrderListBasedOnCustomer(customer), HttpStatus.OK);
	}

	@GetMapping("/orders/list/{id}")
	public ResponseEntity<List<Order>> getOrderListBasedOnMedicineId(@PathVariable("id") String id) {
		logger.info("View all orders based on medicine id");
		List<Order> ord = orderService.getOrderListBasedOnMedicineId(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with medicine id:" + id);
		}
		return new ResponseEntity<>(orderService.getOrderListBasedOnMedicineId(id), HttpStatus.OK);
	}

}