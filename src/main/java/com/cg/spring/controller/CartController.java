package com.cg.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.exception.CartNotFoundException;
import com.cg.spring.model.Cart;
import com.cg.spring.service.ICartService;

@RestController
public class CartController {
	@Autowired
	ICartService cartService;

	@PostMapping("/cart")
	public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
		return new ResponseEntity<>(cartService.addToCart(cart), HttpStatus.OK);
	}

	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> viewAllItems() {
		return new ResponseEntity<>(cartService.viewAllItems(), HttpStatus.OK);
	}

	@DeleteMapping("/cart")
	public void removeAllItems() {
		cartService.removeAllItems();
	}

	@PatchMapping("/cart/{id}")
	public ResponseEntity<Cart> updateItemQuantity(@PathVariable("id") Long cartId, @RequestBody Cart cart) {

		Cart car = cartService.updateItemQuantity(cartId, cart);
		if (car == null) {
			throw new CartNotFoundException("Cart not found with this id to update" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Cart> deleteCartById(@PathVariable("id") Long cartId) {
		Cart car = cartService.deleteCartById(cartId);
		if (car == null) {
			throw new CartNotFoundException("Cart  not found with this id to delete" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@PostMapping("/cart/{cartid}/{custid}/{medid}")
	public Cart addMedicineToCustomerCart(@PathVariable("cartid") Long cartId, @PathVariable("custid") int custId,
			@PathVariable("medid") String medId, @RequestBody Cart cart) {
		return cartService.addMedicineToCustomerCart(medId, custId, custId);
	}

	@DeleteMapping("/deletecart/{cartid}/{custid}/{medid}")
	public Cart removeMedFromCustomerCart(@PathVariable("cartid") Long cartId, @PathVariable("custid") int custId,
			@PathVariable("medid") String medId) {
		return cartService.removeMedFromCustomerCart(medId, custId, custId);
	}

}
