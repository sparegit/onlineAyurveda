package com.cg.spring.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.exception.OrderNotFoundException;
import com.cg.spring.model.Order;
import com.cg.spring.model.Payment;
import com.cg.spring.repository.IOrderRepository;
import com.cg.spring.repository.IPaymentRepository;

@Service
public class PaymentServiceImplementation implements IPaymentService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentServiceImplementation.class);
	
	@Autowired
	IPaymentRepository paymentRepository;
	@Autowired
	IOrderRepository orderRepo;
	// Get a specific payment of the given ID
	@Override
	public Payment findById(int pId) {
		logger.info("View payment by id");
		Optional<Payment> opt = paymentRepository.findById(pId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	// Used to store the given payment passed from the controller
	@Override
	public Payment addPaymentToOrder(int id) {
		logger.info("Adding payment");
	Optional<Order> ord = orderRepo.findById(id);
	if(!ord.isPresent()) {
		throw new OrderNotFoundException("no order found with id:" + id);
	}
	Payment pay=new Payment();
pay.setOrder(ord.get());
	pay.setItemTotal(100);
	pay.setShippingFee(23);
	pay.setTotalPrice(ord.get().getTotalCost()+10);
	pay.setPaymentType("cod");
		return paymentRepository.save(pay);
	}
}
