package com.cg.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.checkout.dto.OrderRequest;
import com.cg.spring.model.Address;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.model.Payment;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.repository.IOrderRepository;
import com.cg.spring.repository.IPaymentRepository;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

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
	ICartService cartService;

	@Autowired
	ICustomerService customerService;
	@Autowired
	ICustomerRepository customerRepo;
	@Autowired
	IPaymentService payService;
	@Autowired
	IPaymentRepository payRepo;

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
	public Order save(int id,Address address) {
		Optional<Customer> cust = customerRepo.findById(id);
		Cart cart = cust.get().getCart();
		if (cart.getMedicineList().size() != 0) {
			LocalDate todaysDate = LocalDate.now();
			LocalDate dispatchdate = todaysDate.plusDays(2);
			List<Medicine> orderList = new ArrayList<Medicine>();
			List<Medicine> cartList = new ArrayList<Medicine>();
			cartList = cart.getMedicineList();
			orderList.addAll(cartList);
			cust.get().setAddress(address);
			Order ord = new Order();
			ord.setCustomer(cust.get());
			ord.setMedicineList(orderList);
			ord.setDispatchDate(dispatchdate);
			ord.setOrderDate(todaysDate);
			ord.setStatus("Placed");
			ord.setLocation(address.getLocation());
//			ord.setLocation(order.getAddress().getLocation());
			double d=cartService.getTotalcost(id);
			ord.setTotalCost((float)d +50);
			ord.setPaymentType("cash on delevery");
			logger.info("Add order to the database");
			cust.get().getOrderListOfaCustomer().add(ord);
//		 payService.save(orderForPayment.getOrderId());
			return  orderRepository.save(ord);
		}
		return null;

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
		ord.setLocation(order.getLocation());
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
	public List<Order> findAllOrderByOrderDate(LocalDate orderDate) {
		logger.info("View all orders by date");
		Optional<List<Order>> opt = Optional.ofNullable(orderRepository.findAllOrderByOrderDate(orderDate));
		if (!opt.isPresent()) {
			return null;
		}
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
	public List<Order> getOrderListBasedOnMedicineId(int medicineid) {
		logger.info("View order list based on medicine id");
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
	// Get order list based on the given customer object
	@Override
	public List<Order> getOrderListBasedOnCustomer(int custId) {
		logger.info("Get order list based on customer");
		Optional<Customer> cust = customerRepo.findById(custId);
		if (cust == null) {
			return null;
		}
		List<Order> orders = cust.get().getOrderListOfaCustomer();


		return orders;
	}

}
