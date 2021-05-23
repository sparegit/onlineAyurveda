package com.cg.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public Order findById(int order) {
		Optional<Order> opt = orderRepository.findById(order);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public Order save(Order order) {
//		Optional<Customer> opt = customerRepo.findById(1);
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findAll() {

		return orderRepository.findAll();
	}

	@Override
	public Order update(Order order) {
		Optional<Order> opt = orderRepository.findById(order.getOrderId());
		if (!opt.isPresent()) {
			return null;
		}
		Order ord = opt.get();
		ord.setStatus(order.getStatus());
		ord.setTotalCost(order.getTotalCost());
		return orderRepository.save(ord);
	}

	@Override
	public Order cancelOrder(int id) {
		Optional<Order> opt = orderRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		Order ord = opt.get();
		orderRepository.deleteById(id);
		return ord;

	}

	@Override
	public double calculateTotalCost(int orderId) {
		Optional<Order> opt = orderRepository.findById(orderId);
		if (!opt.isPresent()) {
			return 0.0f;
		}
		Order ord = opt.get();
		double total = ord.getTotalCost();
		return total;
	}

	@Override
	public List<Order> findAllOrderByOrderDate(String orderDate) {
		Optional<List<Order>> opt = Optional.ofNullable(orderRepository.findAllOrderByOrderDate(orderDate));
		if (!opt.isPresent()) {
			return null;
		}
		List<Order> ord = opt.get();
		return ord;
	}

	@Override
	public Order updateOrderStatusByUserId(int id, Order order) {
		Optional<Order> ord = orderRepository.findById(id);
		if (!ord.isPresent()) {
			return null;
		}
		ord.get().setStatus(order.getStatus());
		return orderRepository.save(ord.get());
	}

	@Override
	public List<Order> getOrderListBasedOnMedicineId(String medicineid) {
		Optional<Medicine> med = medicineRepo.findById(medicineid);
		if (!med.isPresent()) {
			return null;
		}
		List<Order> orders = medicineServ.getOrderList();
		List<Order> ordersWithMedId = new ArrayList<Order>();
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

	@Override
	public List<Order> getOrderListBasedOnCustomer(Customer customer) {
		Optional<Customer> cust = customerRepo.findById(customer.getCustomerId());
		if (cust == null) {
			return null;
		}
		List<Order> orders = customerService.showAllOrders();
		List<Order> ordersOfCustomer = new ArrayList<Order>();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCustomer().getCustomerId() == cust.get().getCustomerId()) {
				ordersOfCustomer.add(orders.get(i));
			}
		}

		return ordersOfCustomer;
	}

}
