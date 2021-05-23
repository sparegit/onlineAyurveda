package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Cart;
import com.cg.spring.model.Customer;
import com.cg.spring.model.Medicine;
import com.cg.spring.repository.ICartRepository;
import com.cg.spring.repository.ICustomerRepository;
import com.cg.spring.repository.IMedicineRepository;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	ICartRepository cartRepo;

	@Autowired
	IMedicineRepository medRepo;

	@Autowired
	ICustomerRepository custRepo;

	@Override
	public Cart addToCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public List<Cart> viewAllItems() {
		return cartRepo.findAll();
	}

	@Override
	public void removeAllItems() {
		cartRepo.deleteAll();

	}

	@Override
	public Cart updateItemQuantity(Long cartId, Cart cart) {
		Optional<Cart> opt = cartRepo.findById(cartId);
		if (!opt.isPresent()) {
			return null;
		}
		Cart cart1 = opt.get();
		cart1.setPrice(cart.getPrice());
		cart1.setMedicineName(cart.getMedicineName());
		cart1.setQuantity(cart.getQuantity());
		return cartRepo.save(cart);
	}

	@Override
	public Cart deleteCartById(Long cartid) {
		Optional<Cart> opt = cartRepo.findById(cartid);
		if (!opt.isPresent()) {
			return null;
		}
		Cart car = opt.get();
		cartRepo.deleteById(cartid);
		return car;
	}

	@Override
	public Cart addMedicineToCustomerCart(String medId, int custId, long cartId) {
		Optional<Medicine> med = medRepo.findById(medId);
		Optional<Customer> cust = custRepo.findById(custId);
		Optional<Cart> cart = cartRepo.findById(cartId);

		if (!med.isPresent() || !cust.isPresent() || !cart.isPresent()) {
			return null;
		}
		Customer dbcustomer = cust.get();
		Medicine dbmedicine = med.get();
		// Cart dbcart = cart.get();

		// add medicine to customer cart
		Cart cart1 = dbcustomer.getCart();
		cart1.getMedicine().add(dbmedicine);

		return cartRepo.save(cart1);
	}

	@Override
	public Cart removeMedFromCustomerCart(String medId, int custId, long cartId) {
		Optional<Medicine> med = medRepo.findById(medId);
		Optional<Customer> cust = custRepo.findById(custId);
		Optional<Cart> cart = cartRepo.findById(cartId);

		if (!med.isPresent() || !cust.isPresent() || !cart.isPresent()) {
			return null;
		}
		Customer dbcustomer = cust.get();
		Medicine dbmedicine = med.get();
		// Cart dbcart = cart.get();

		Cart cart1 = dbcustomer.getCart();
		cart1.getMedicine().remove(dbmedicine);

		return cartRepo.save(cart1);

	}

	@Override
	public Cart updateCustomerCartMedicineQuantity(int medId, int custId, int cartId, int quantity) {

		return null;
	}
}
