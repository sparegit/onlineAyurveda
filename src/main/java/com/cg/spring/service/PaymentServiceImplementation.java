package com.cg.spring.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Payment;
import com.cg.spring.repository.IPaymentRepository;

@Service
public class PaymentServiceImplementation implements IPaymentService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentServiceImplementation.class);
	
	@Autowired
	IPaymentRepository paymentRepository;

	@Override
	public Payment findById(int pId) {
		logger.info("View payment by id");
		Optional<Payment> opt = paymentRepository.findById(pId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public Payment save(Payment pay) {
		logger.info("Adding payment");
		return paymentRepository.save(pay);
	}
}
