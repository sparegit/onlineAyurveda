package com.cg.spring.service;

import com.cg.spring.model.Payment;

public interface IPaymentService {
	// Method to be override by the implementing class
		public Payment addPaymentToOrder(int pay);

	// Method to be override by the implementing class
		public Payment findById(int pId);

}
