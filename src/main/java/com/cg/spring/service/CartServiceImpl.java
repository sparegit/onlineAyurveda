package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.dto.Cartdto;
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

	@Override
	public Cart addItemToCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public List<Cart> viewAllItems() {
		return cartRepo.findAll();
	}


	@Override
	public Medicine UpdateMedQuantity(int medId,int quantity) {
		Optional<Medicine> med = medRepo.findById(medId);
		if (!med.isPresent()) {
			return null;
		}
		Medicine med1 = med.get();
		med1.getMedicineQuantity();
		med1.setMedicineQuantity(quantity);
		return medRepo.save(med1);
	}

	
	@Override
	public Cart removeCartItem(long cartId) {
		Optional<Cart> opt = cartRepo.findById(cartId);
		if (!opt.isPresent()) {
			return null;
		}
		Cart cart = opt.get();
		cartRepo.deleteById(cartId);
		return cart;
	}
		 
	}
	

	
		


