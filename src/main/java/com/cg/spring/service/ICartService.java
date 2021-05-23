package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.model.Cart;

@Service
public interface ICartService {
	public Cart addToCart(Cart cart);

	public List<Cart> viewAllItems();

	public void removeAllItems();

	public Cart deleteCartById(Long cartid);

	public Cart updateItemQuantity(Long cartid, Cart cart);

	public Cart addMedicineToCustomerCart(String medId, int custId, long cartId);

	public Cart removeMedFromCustomerCart(String medId, int custId, long cartId);

	public Cart updateCustomerCartMedicineQuantity(int medId, int custId, int cartId, int quantity);

}
