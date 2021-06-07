package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Cart;
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
}

	//@Autowired
	//ICustomerRepository custRepo;
	// Used to Store the cart passed as parameter from the Controller function
/*	@Override
	public Cart addItemToCart(Medicine medicine) {
		logger.info("Adding medicines to the cart");
		Optional<Medicine> med = medRepo.findById(medicine.getMedicineId());
		
		if(!med.isPresent()) {
			return null;
		}
		Cart cart = new Cart();
		cart.getMedicineList().add(medicine);
		return cartRepo.save(cart);
	}
}*/

	/*@Override
	public List<Cart> viewAllItems() {
		return cartRepo.findAll();
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
}

/*	@Override
	//public Cart deleteCartById(Long cartid) {
		Optional<Cart> opt = cartRepo.findById(cartid);
		if (!opt.isPresent()) {
			return null;
		}
		Cart car = opt.get();
		cartRepo.deleteById(car.getCartId());
		return car;
	}

	@Override
	public Cart removeItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}*/
