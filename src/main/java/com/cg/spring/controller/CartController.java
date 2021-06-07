package com.cg.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.CartNotFoundException;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Medicine;
import com.cg.spring.service.ICartService;

@RestController
public class CartController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CartController.class);
	// We are autowiring the cart service layer to this controller layer of cart
	@Autowired
	ICartService cartService;

	// Add
	@PostMapping("/cart")
	public ResponseEntity<Cart> addItemToCart(@Valid @RequestBody Cart cart ) {
		return new ResponseEntity<>(cartService.addItemToCart(cart), HttpStatus.OK);
	}

	@GetMapping("/cart/viewAllItems")
	public ResponseEntity<List<Cart>> viewAllItems() {
		logger.info("Viewed Successfully");
		List<Cart> cart =cartService.viewAllItems();
		return ResponseEntity.ok(cart);
	}
	
	/**
	 * This below function is used to update a cart based on the given
	 * medicineId and redirects to the Cart service
	 */

	@PatchMapping("/cart/update")
	public ResponseEntity<Medicine> UpdateMedQuantity(int medId, int quantity) {
		logger.info("Successfully Updated" + medId, quantity);
		Medicine medicine = cartService.UpdateMedQuantity(medId, quantity);
		return ResponseEntity.ok(medicine);
	}
	

	// Delete
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Cart> deleteCartById(@PathVariable("id") Long cartId) {
		Cart car = cartService.removeCartItem(cartId);
		if (car == null) {
			throw new CartNotFoundException("Cart  not found with this id to delete" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

}

	
	
	




