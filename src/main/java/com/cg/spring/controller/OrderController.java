package com.cg.spring.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.checkout.dto.OrderRequest;
import com.cg.spring.exception.OrderNotFoundException;
import com.cg.spring.model.Address;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;
import com.cg.spring.service.IOrderService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(OrderController.class);
	// We are autowiring the order service layer to this controller layer of order
	@Autowired
	IOrderService orderService;
	
	// This controller is used to get a specific order on basis of ID
	@GetMapping("/order/{id}")
	public ResponseEntity<List<Order>> findOrderById(@PathVariable("id") int ordId) {
		logger.info("View order by id");
		Order ord = orderService.findById(ordId);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to find:" + ordId);
		}
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);

	}
	// This controller is used to return and list all the orders found in the
		// database and request to the service to perform the action
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> findAll() {
		logger.info("View all orders");
		return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
	}

	// This controller is used to create a new or add new order and redirects it
	// to the service layer
	@PostMapping("/order/{id}")
	public ResponseEntity<Order> addOrder(@Valid @PathVariable("id") int order, @RequestBody Address address) {
		logger.info("Add order in database");
		return new ResponseEntity<>(orderService.save(order,address), HttpStatus.OK);
	}
	// this controller function perform cancellation of a specific given order
	// and request the service to perform the action and returns the message as
	// cancelled else throw exception
	@PostMapping("/order/cancel/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("id") int id) {
		logger.info("Cancel order by id");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to cancel:" + id);
		}
		return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
	}
	// This function is used to update a specific order on basis of given
	// order id and returns exception if given order id is not found.
	@PutMapping("/order/update/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @Valid @RequestBody Order order) {
		logger.info("Update order in the database");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to update:" + id);
		}
		return new ResponseEntity<>(orderService.update(ord), HttpStatus.OK);
	}
	// This function is used to update a specific status on basis of given
		// user id and returns exception if given order id is not found.
	@PatchMapping("/order/update/{id}")
	public ResponseEntity<Order> updateStatusByUserId(@PathVariable("id") int id ,@Valid @RequestBody Order order) {
		logger.info("Update order status by userid");
		Order ord = orderService.findById(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with id to update status:" + id);
		}
		return new ResponseEntity<>(orderService.updateOrderStatusByUserId(id, order), HttpStatus.OK);
	}
	// This controller is used to return and list all the orders found in the
		// database based on the date and request to the service to perform the action
	@GetMapping("/orders/date/{orderDate}")
	public ResponseEntity<List<Order>> findAllOrderByDate(@PathVariable("orderDate") LocalDate orderDate) {
		logger.info("Get all orders by date");
		return new ResponseEntity<>(orderService.findAllOrderByOrderDate(orderDate), HttpStatus.OK);
	}

	@GetMapping("/orders/list/cust/{id}")
	public ResponseEntity<List<Order>> getOrderListBasedOnCustomer(@Valid @PathVariable("id") int customer) {
		logger.info("View all orders based on customers");
		List<Order> ord = orderService.getOrderListBasedOnCustomer(customer);

		if (ord == null) {
			throw new OrderNotFoundException("Order not found with customer name:" + customer);
		}
		return new ResponseEntity<>(orderService.getOrderListBasedOnCustomer(customer), HttpStatus.OK);
	}
	// This controller is used to return and list all the orders found in the
	// database based on medicine id and request to the service to perform the
	// action
	@GetMapping("/orders/list/{id}")
	public ResponseEntity<List<Order>> getOrderListBasedOnMedicineId(@PathVariable("id") int id) {
		logger.info("View all orders based on medicine id");
		List<Order> ord = orderService.getOrderListBasedOnMedicineId(id);
		if (ord == null) {
			throw new OrderNotFoundException("Order not found with medicine id:" + id);
		}
		return new ResponseEntity<>(orderService.getOrderListBasedOnMedicineId(id), HttpStatus.OK);
	}

}
