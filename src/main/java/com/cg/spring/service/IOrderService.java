package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Order;

public interface IOrderService {

	public Order save(Order order);

	public Order findById(int order);

	public Order update(Order order);

	public List<Order> findAll();

	public Order cancelOrder(int id);

	public List<Order> getOrderListBasedOnMedicineId(String medicineid);

	public List<Order> findAllOrderByOrderDate(String orderDate);

	public double calculateTotalCost(int orderid);

	public Order updateOrderStatusByUserId(int id, Order order);

	public List<Order> getOrderListBasedOnCustomer(Customer customer);
}
