package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;

public interface IOrderService {

	// Method to be override by the implementing class
		public Order save(Order order);

		// Method to be override by the implementing class
		public Order findById(int order);

		// Method to be override by the implementing class
		public Order update(Order order);

		// Method to be override by the implementing class
		public List<Order> findAll();

		// Method to be override by the implementing class
		public Order cancelOrder(int id);

		// Method to be override by the implementing class
		public List<Order> getOrderListBasedOnMedicineId(String medicineid);

		// Method to be override by the implementing class
		public List<Order> findAllOrderByOrderDate(String orderDate);

		// Method to be override by the implementing class
		public double calculateTotalCost(int orderid);

		// Method to be override by the implementing class
		public Order updateOrderStatusByUserId(int id, Order order);

		// Method to be override by the implementing class
		public List<Order> getOrderListBasedOnCustomer(Customer customer);
}
