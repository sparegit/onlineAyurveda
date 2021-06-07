package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.dto.Cartdto;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Medicine;

@Service
public interface ICartService {
	
	public Cart addItemToCart(Cart cart);
	
	public List<Cart> viewAllItems();
	
	public Cart removeCartItem(long cartid);

	public Medicine UpdateMedQuantity(int medId, int quantity);
	
	
	
}
 