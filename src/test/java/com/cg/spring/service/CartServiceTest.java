package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Cart;
import com.cg.spring.model.Medicine;

@SpringBootTest
class CartServiceTest {
	@Autowired
	ICartService cartService;

	@Test
	@Disabled
	void testaddToCart() {
		Cart cart = new Cart();
		Cart persistedCart = cartService.addToCart(cart);
		assertEquals("kayatrimeni", persistedCart.getMedicineName());
	}

	@Test
	@Disabled
	void testViewAllItems() {
		List<Cart> cart = cartService.viewAllItems();
		assertEquals(1, cart.size());
	}

	@Test
	@Disabled
	void testremoveAllItems() {
		cartService.removeAllItems();
	}

	@Test
	@Disabled
	void testupdateItemQuantity() {
		Cart cart = new Cart();
		cart.setCartId(114L);
		cart.setMedicineName("kottamchukkadhi");
		cart.setPrice(450);
		cart.setQuantity(2);
		cart.setTotalAmount(900);

		Cart cart1 = cartService.updateItemQuantity(114L, cart);
		assertEquals(114L, cart1.getCartId());
	}

	@Test
	@Disabled
	void testDeleteById() {
		Cart cart = new Cart();
		Cart persistedCart = cartService.deleteCartById(115L);

		assertEquals(115L, persistedCart.getCartId());
		assertEquals("rasnaadhi", persistedCart.getMedicineName());
		assertEquals(1, persistedCart.getQuantity());
		assertEquals(100, persistedCart.getPrice());
		assertEquals(100, persistedCart.getTotalAmount());
	}

	@Test
	@Disabled
	void addMedicineToCart() {
		// Customer cust = new
		// Customer(1,"Lara","lara43*l",98766087760L,"lara44@gmail.com");
		Medicine m1 = new Medicine("3", "Chandana", 600f, LocalDate.parse("2020-03-13"), LocalDate.parse("2022-06-22"));
		Medicine m2 = new Medicine("2", "chandana", 499f, LocalDate.parse("2020-03-13"), LocalDate.parse("2022-06-22"));
		List<Medicine> medicine = Stream.of(m1, m2).collect(Collectors.toList());
		// Cart cart = new Cart(115L,1,"kayatrimeni",1,299,299,cust);

	}
}
