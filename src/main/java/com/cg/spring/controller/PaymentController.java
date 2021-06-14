package com.cg.spring.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PaymentController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentController.class);
	// We are autowiring the payment service layer to this controller layer of
		// payment
	@Autowired
	IPaymentService paymentService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IPaymentRepository paymentRepository;
	// This controller is used to get a specific payment on basis of ID
	@GetMapping("/order/payment/{id}")
	public ResponseEntity<Payment> findPaymentBypId(@PathVariable("id") int pId) {
		logger.info("View payment by id");
		Payment pay = paymentService.findById(pId);
		if (pay == null) {
			throw new OrderNotFoundException("payment not found with id to find:" + pId);
		}
		return new ResponseEntity<>(paymentService.findById(pId), HttpStatus.OK);

	}
	// This controller is used to create a new or add new payment and redirects it
		// to the service layer
	@PostMapping("/orders/payment/placeorder/{id}")
	public ResponseEntity<Payment> placeOrder(@Valid  @PathVariable("id") int req) {
		logger.info("Place order");
		return new ResponseEntity<>(paymentService.addPaymentToOrder(req), HttpStatus.OK);
	}

}
