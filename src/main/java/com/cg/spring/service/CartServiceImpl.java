package com.cg.spring.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
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

	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CartServiceImpl.class);

	@Autowired
	ICartRepository cartRepo;

	@Autowired
	IMedicineRepository medRepo;

	@Autowired
	ICustomerRepository custRepo;

	// Used to add a cart
	@Override
	public Cart addCart(Cart cart) {
		logger.info("Adding cart in the database");
		return cartRepo.save(cart);
	}

	// Used to view all carts
	@Override
	public List<Cart> viewAllItems() {
		logger.info("View all carts");
		return cartRepo.findAll();
	}

	// Update medicine quantity
	@Override
	public Medicine UpdateMedQuantity(int medId, int quantity) {
		logger.info("Updating medicine quantity with mediicine id ");
		Optional<Medicine> med = medRepo.findById(medId);
		if (!med.isPresent()) {
			return null;
		}
		Medicine med1 = med.get();
		med1.getMedicineQuantity();
		med1.setMedicineQuantity(quantity);
		return medRepo.save(med1);
	}

	// Remove the cart from database
	@Override
	public Cart removeCartItem(long cartId) {
		logger.info("View all carts");
		Optional<Cart> opt = cartRepo.findById(cartId);
		if (!opt.isPresent()) {
			return null;
		}
		Cart cart = opt.get();
		cartRepo.deleteById(cartId);
		return cart;
	}

	// Adding a cart to the customer
	@Override
	public Cart addCartToCustomer(int custId) {
		logger.info("adding Cart to customer");
		Optional<Customer> cust = custRepo.findById(custId);
		if (!cust.isPresent()) {
			return null;
		}
		Cart cart = new Cart();
		cust.get().setCart(cart);
		cart.setCartId(cart.getCartId());
		cart.setMedicineList(cart.getMedicineList());
		cart.setPrice(cart.getPrice());
		cart.setQuantity(cart.getQuantity());
		cart.setTotalAmount(cart.getTotalAmount());

		return cartRepo.save(cart);
	}

	// Add products to the cart
	@Override
	public void addProduct(Medicine product, int custId) {
		logger.info("Adding products to the cart");
		Optional<Customer> cust = custRepo.findById(custId);
		Cart cart = cust.get().getCart();
		if (cart.getMedicineList().size() == 0) {
			cart.getMedicineList().add(product);
			cartRepo.save(cart);
		} else
			for (int i = 0; i < cart.getMedicineList().size(); i++) {
				if (cart.getMedicineList().get(i).getMedicineId() == product.getMedicineId()) {
					cart.getMedicineList().get(i).setMedicineQuantity(product.getMedicineQuantity() + 1);
					medRepo.save(cart.getMedicineList().get(i));

				} else {
					cart.getMedicineList().add(product);
				}
			}
	}

	// View the products in the cart
	@Override
	public List<Medicine> getProductsInCart(int custId) {
		logger.info("View all products from the cart");
		Optional<Customer> cust = custRepo.findById(custId);
		Cart cart = cust.get().getCart();

		return Collections.unmodifiableList(cart.getMedicineList());
	}

	// Remove product from the cart
	@Override
	public void removeProduct(Medicine product, int custId) {
		logger.info("Remove products from the cart");
		Optional<Customer> cust = custRepo.findById(custId);
		Cart cart = cust.get().getCart();

		for (int i = 0; i < cart.getMedicineList().size(); i++) {
			if (cart.getMedicineList().get(i).getMedicineId() == product.getMedicineId() && cart.getMedicineList().get(i).getMedicineQuantity()!=1) {
				cart.getMedicineList().get(i).setMedicineQuantity(product.getMedicineQuantity() - 1);
				medRepo.save(cart.getMedicineList().get(i));
			} else {
				for (int j = 0; j < cart.getMedicineList().size(); j++)
				if(cart.getMedicineList().get(j).getMedicineId()==product.getMedicineId()) {
					cart.getMedicineList().remove(j);
				}
			}
		}

	}
}
