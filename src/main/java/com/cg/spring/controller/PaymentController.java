package com.cg.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.checkout.dto.OrderRequest;
import com.cg.spring.exception.OrderNotFoundException;
import com.cg.spring.model.Payment;
import com.cg.spring.repository.IPaymentRepository;
import com.cg.spring.service.IOrderService;
import com.cg.spring.service.IPaymentService;

@RestController
public class PaymentController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentController.class);
	
	@Autowired
	IPaymentService paymentService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IPaymentRepository paymentRepository;

	@GetMapping("/order/payment/{id}")
	public ResponseEntity<Payment> findPaymentBypId(@PathVariable("id") int pId) {
		logger.info("View payment by id");
		Payment pay = paymentService.findById(pId);
		if (pay == null) {
			throw new OrderNotFoundException("payment not found with id to find:" + pId);
		}
		return new ResponseEntity<>(paymentService.findById(pId), HttpStatus.OK);

	}

	@PostMapping("/orders/payment/placeorder")
	public ResponseEntity<Payment> placeOrder(@RequestBody OrderRequest req) {
		logger.info("Place order");
		return new ResponseEntity<>(paymentService.save(req.getPayment()), HttpStatus.OK);
	}

}
