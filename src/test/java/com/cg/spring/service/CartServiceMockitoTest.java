package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.spring.model.Cart;
import com.cg.spring.repository.ICartRepository;

@ExtendWith(SpringExtension.class)
class CartServiceMockitoTest {

	@InjectMocks
	CartServiceImpl cartService;

	@MockBean
	ICartRepository cartRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Disabled
	void testaddToCart() {
		Cart cart = new Cart(115L, "kayatrimeni", 1, 299, 299);
		Mockito.when(cartRepo.save(cart)).thenReturn(cart);

		assertEquals(115L, cart.getCartId());
	}

	@Test
	@Disabled
	void testviewAllItems() {
		Cart cart1 = new Cart(112L,"salacia", 1, 65, 65);
		Cart cart2 = new Cart(113L,"livex", 2, 30, 60);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		Mockito.when(cartRepo.findAll()).thenReturn(cartList);
		List<Cart> cart = cartService.viewAllItems();
		assertEquals(2, cart.size());
	}

	@Test
	@Disabled
	void testupdateItemQuantity() {
		Cart cart = new Cart(113L, "kayatrimeni", 2, 100, 200);
		Mockito.when(cartRepo.findById(113L)).thenReturn(Optional.of(cart));
		Mockito.when(cartRepo.save(cart)).thenReturn(cart);
		Cart cart1 = cartService.updateItemQuantity(113L, cart);
		assertEquals("kayatrimeni", cart1.getMedicineName());
	}

	@Test
	@Disabled
	void testdeleteById() {
		Cart cart = new Cart(115L,"rasnaadhi", 1, 100, 100);
		Mockito.when(cartRepo.findById(115L)).thenReturn(Optional.of(cart));
		cartService.deleteCartById(115L);
		assertEquals(115L, cart.getCartId());
	}

}
