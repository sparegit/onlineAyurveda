package com.cg.spring.service;

import com.cg.spring.model.Payment;

public interface IPaymentService {
	public Payment save(Payment pay);

	public Payment findById(int pId);
//	public Order update(Order order) ;
//	public List<Payment> findAll();
//	public Order cancelOrder(int id) ;
//	public List<Order> showAllOrders(int medicineid);
//	public List<Order> findAllOrderByOrderDate(String orderDate);
//	public double calculateTotalCost(int orderid);
//	public Order updateOrderStatusByUserId(int id, Order order);
}
