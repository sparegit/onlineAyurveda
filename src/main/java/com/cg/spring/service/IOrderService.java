package com.cg.spring.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.spring.checkout.dto.OrderRequest;
import com.cg.spring.model.Address;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;

public interface IOrderService {

	// Method to be override by the implementing class
		public Order save(int order, Address adress);

		// Method to be override by the implementing class
		public Order findById(int order);

		// Method to be override by the implementing class
		public Order update(Order order);

		// Method to be override by the implementing class
		public List<Order> findAll();

		// Method to be override by the implementing class
		public Order cancelOrder(int id);

		// Method to be override by the implementing class
		public List<Order> getOrderListBasedOnMedicineId(int medicineid);

		// Method to be override by the implementing class
		public List<Order> findAllOrderByOrderDate(LocalDate orderDate);

		// Method to be override by the implementing class
		public double calculateTotalCost(int orderid);

		// Method to be override by the implementing class
		public Order updateOrderStatusByUserId(int id, Order order);

		// Method to be override by the implementing class
		public List<Order> getOrderListBasedOnCustomer(int custId);
}
