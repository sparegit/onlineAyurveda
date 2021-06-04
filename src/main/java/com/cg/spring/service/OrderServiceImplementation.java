package com.cg.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class OrderServiceImplementation implements IOrderService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(OrderServiceImplementation.class);
	
	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	IMedicineRepository medicineRepo;
	@Autowired
	IMedicineService medicineServ;

	@Autowired
	ICustomerService customerService;
	@Autowired
	ICustomerRepository customerRepo;
	// Get a specific order based on the given id.
	@Override
	public Order findById(int order) {
		logger.info("View order by id");
		Optional<Order> opt = orderRepository.findById(order);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	// used to add or place the order
	@Override
	public Order save(Order order) {
		logger.info("Add order to the database");
		return orderRepository.save(order);
	}
	// Used to list all the orders from the database
	@Override
	public List<Order> findAll() {
		logger.info("View all orders");
		return orderRepository.findAll();
	}
	// Used to update the order of given object
	@Override
	public Order update(Order order) {
		logger.info("Update order details in database");
		Optional<Order> opt = orderRepository.findById(order.getOrderId());
		if (!opt.isPresent()) {
			return null;
		}
		Order ord = opt.get();
		ord.setStatus(order.getStatus());
		ord.setTotalCost(order.getTotalCost());
		return orderRepository.save(ord);
	}
	// Used to cancel the order
	@Override
	public Order cancelOrder(int id) {
		logger.info("Cancel order by id");
		Optional<Order> opt = orderRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		Order ord = opt.get();
		orderRepository.deleteById(id);
		return ord;

	}
	// Used to calculate the total cost
	@Override
	public double calculateTotalCost(int orderId) {
		logger.info("Calculating cost of order by id");
		Optional<Order> opt = orderRepository.findById(orderId);
		if (!opt.isPresent()) {
			return 0.0f;
		}
		Order ord = opt.get();
		double total = ord.getTotalCost();
		return total;
	}
	// Used to list all the orders from the database
	@Override
	public List<Order> findAllOrderByOrderDate(String orderDate) {
		logger.info("View all orders by date");
		Optional<List<Order>> opt = Optional.ofNullable(orderRepository.findAllOrderByOrderDate(orderDate));
		if (!opt.isPresent()) {
			return null;
		}
		List<Order> ord = opt.get();
		return orderRepository.findAllOrderByOrderDate(orderDate);
	}
	// Used to update the order by using id and object
	@Override
	public Order updateOrderStatusByUserId(int id, Order order) {
		logger.info("View order status by id");
		Optional<Order> ord = orderRepository.findById(id);
		if (!ord.isPresent()) {
			return null;
		}
		ord.get().setStatus(order.getStatus());
		return orderRepository.save(ord.get());
	}
	// Get order list based on the given medicine id.
	@Override
	public List<Order> getOrderListBasedOnMedicineId(String medicineid) {
		logger.info("View order list based on medicine id");
		Optional<Medicine> med = medicineRepo.findById(medicineid);
		if (!med.isPresent()) {
			return null;
		}
		List<Order> orders = medicineServ.getOrderList();
		List<Order> ordersWithMedId = new ArrayList();
		for (int i = 0; i < orders.size(); i++) {
			List<Medicine> medicines = orders.get(i).getMedicineList();
			for (int j = 0; j < medicines.size(); j++) {
				if (medicines.get(j).getMedicineId() == medicineid) {
					ordersWithMedId.add(orders.get(i));
				}
			}
		}
		return ordersWithMedId;
	}
	// Get order list based on the given customer object
	@Override
	public List<Order> getOrderListBasedOnCustomer(Customer customer) {
		logger.info("Get order list based on customer");
		Optional<Customer> cust = customerRepo.findById(customer.getCustomerId());
		if (cust == null) {
			return null;
		}
		List<Order> orders = customerService.showAllOrders();
		List<Order> ordersOfCustomer = new ArrayList();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().getCustomerId() == cust.get().getCustomerId()) {
				ordersOfCustomer.add(orders.get(i));
			}
		}

		return ordersOfCustomer;
	}

}
