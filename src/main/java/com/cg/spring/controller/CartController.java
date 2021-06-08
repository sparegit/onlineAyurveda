package com.cg.spring.controller;

import java.util.List;
import java.util.Optional;

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
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.service.ICartService;

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

	// This controller is used to create a new or add new cart and redirects it
	// to the service layer
	@PostMapping("/cart")
	public ResponseEntity<Cart> addItemToCart(@Valid @RequestBody Cart cart) {
		logger.info("Adding items to cart");
		return new ResponseEntity<>(cartService.addCart(cart), HttpStatus.OK);
	}

	// This controller is used to view all carts and redirects it
	// to the service layer
	@GetMapping("/cart/viewAllItems")
	public ResponseEntity<List<Cart>> viewAllItems() {
		logger.info("Viewed Successfully");
		return new ResponseEntity<>(cartService.viewAllItems(), HttpStatus.OK);
	}

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

	// This controller is used to create a new or add new cart to a customer and
	// redirects it
	// to the service layer
	@PostMapping("/addcarttocustomer/{id}")
	public ResponseEntity<Cart> addCartToCustomer(@PathVariable("id") int custId) {
		logger.info("Adding cart to a customer");
		Cart cart = cartService.addCartToCustomer(custId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	// This controller is used to add products to a cart of a customer and redirects
	// it
	// to the service layer
	@PostMapping("/shoppingCart/addProduct/{productId}/{custId})")
	public String addProductToCart(@PathVariable("productId") int productId, @PathVariable("custId") int custId) {
		logger.info("Adding products to the cart");
		Optional<Medicine> med = medRepo.findById(productId);
		if (med.isPresent()) {
			cartService.addProduct(med.get(), custId);

			return "added to cart";
		}
		return "out of stock";
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
	public String removeproductfromCart(@PathVariable("productId") int productId, @PathVariable("custId") int custId) {
		logger.info("Removing products from the cart");
		Optional<Medicine> med = medRepo.findById(productId);
		if (med.isPresent()) {
			cartService.removeProduct(med.get(), custId);

			return "removed from cart";
		}
		return "your cart is empty";
	}

}
