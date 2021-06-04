package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.model.Cart;
import com.cg.spring.model.Medicine;

@Service
public interface ICartService {
	public Cart addItemToCart(Medicine medicine );
}
	/*public Cart removeItem(int id);
	public List<Cart> viewAllItems();
	 
	


	public Cart updateItemQuantity(Long cartid, Cart cart);
}*/
 