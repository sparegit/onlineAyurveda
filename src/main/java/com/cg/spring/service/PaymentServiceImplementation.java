package com.cg.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Payment;
import com.cg.spring.repository.IPaymentRepository;

@Service
public class PaymentServiceImplementation implements IPaymentService {
	@Autowired
	IPaymentRepository paymentRepository;

	@Override
	public Payment findById(int pId) {
		Optional<Payment> opt = paymentRepository.findById(pId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public Payment save(Payment pay) {

		return paymentRepository.save(pay);
	}
}