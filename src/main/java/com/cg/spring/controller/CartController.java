package com.cg.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.cg.spring.model.Customer;
import com.cg.spring.model.Medicine;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.service.ICartService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CartController {

	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CartController.class);
	// We are autowiring the cart service layer to this controller layer of cart
	@Autowired
	ICartService cartService;
	// We are autowiring the medicine repository layer to this controller layer of
	// cart
	@Autowired
	IMedicineRepository medRepo;
@Autowired
ICustomerRepository custrepo;
	// This controller is used to update the medicine quantity with reference
	// medicine id and redirects it
	// to the service layer
	@PatchMapping("/cart/update")
	public ResponseEntity<Medicine> UpdateMedQuantity(int medId, int quantity) {
		logger.info("Successfully Updated" + medId, quantity);
		return new ResponseEntity<>(cartService.UpdateMedQuantity(medId, quantity), HttpStatus.OK);
	}

	// This controller is used to delete a cart and redirects it
	// to the service layer
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Cart> deleteCartById(@PathVariable("id") Long cartId) {
		logger.info("Deleting the cart by id");
		Cart car = cartService.removeCartItem(cartId);
		if (car == null) {
			throw new CartNotFoundException("Cart  not found with this id to delete" + cartId);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}


	// This controller is used to add products to a cart of a customer and redirects
	// it
	// to the service layer
	@PostMapping("/shoppingCart/addProduct/{productId}/{custId}")
	public List<Medicine> addProductToCart(@PathVariable("productId") int productId, @PathVariable("custId") int custId) {
		logger.info("Adding products to the cart");
		Optional<Medicine> med = medRepo.findById(productId);
		if (med.isPresent()) {
	
			return cartService.addProduct(med.get(), custId); 
		}else
			
		return cartService.getProductsInCart(custId); 
	}

	// This controller is used to view all the products in a cart and redirects it
	// to the service layer
	@GetMapping("/getproducts/cart/{custId}")
	public List<Medicine> getProductsFromCart(@PathVariable("custId") int custId) {
		logger.info("Getting products in the cart");
		return cartService.getProductsInCart(custId);

	}
	

	// This controller is used to delete a product from the cart and redirects it
	// to the service layer
	@DeleteMapping("/removecartitem/{productId}/{custId}")
	public List<Medicine> removeproductfromCart(@PathVariable("productId") int productId, @PathVariable("custId") int custId) {
		logger.info("Removing products from the cart");
		Optional<Medicine> med = medRepo.findById(productId);
		if (med.isPresent()) {
			cartService.removeProduct(med.get(), custId);
		}
		return cartService.getProductsInCart(custId);
	}
	@GetMapping("/gettotal/cart/{custId}")
	public double getTotalOfCart(@PathVariable("custId") int custId) {
		logger.info("Getting products in the cart");
		return cartService.getTotalcost(custId);

	}
}
