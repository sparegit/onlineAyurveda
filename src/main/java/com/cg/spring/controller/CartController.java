/*package com.cg.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	//@PostMapping("/cart")
	//public ResponseEntity<Cart> addItemToCart(@RequestBody Medicine medicine ) {
		//return new ResponseEntity<>(cartService.addItemToCart(medicine), HttpStatus.OK);
	}

/*	// ViewAll
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> viewAllItems() {
		return new ResponseEntity<>(cartService.viewAllItems(), HttpStatus.OK);
	}

	// DeleteAll
	@DeleteMapping("/cart")
	public void removeAllItems() {
		cartService.removeAllItems();
	}

	// Update
	@PutMapping("/cart/{id}")
	public ResponseEntity<Cart> updateItemQuantity(@PathVariable("id") Long cartId, @RequestBody Cart cart) {

		Cart car = cartService.updateItemQuantity(cartId, cart);
		if (car == null) {
			throw new CartNotFoundException("Cart not found with this id to update" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	// Delete
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Cart> deleteCartById(@PathVariable("id") Long cartId) {
		Cart car = cartService.deleteCartById(cartId);
		if (car == null) {
			throw new CartNotFoundException("Cart  not found with this id to delete" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
*/

