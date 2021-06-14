package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.dto.Cartdto;
import com.cg.spring.model.Cart;
import com.cg.spring.model.Medicine;

@Service
public interface ICartService {
  
	public Cart addCart(Cart cart);

	public List<Cart> viewAllItems();
	
	public double getTotalcost(int custId);

	public Cart removeCartItem(long cartid);

	public Medicine UpdateMedQuantity(int medId, int quantity);

	public Cart addCartToCustomer(int custId);

	public List<Medicine> addProduct(Medicine product, int custId);

	public List<Medicine> getProductsInCart(int custId);

	public List<Medicine> removeProduct(Medicine product, int custId);


}
