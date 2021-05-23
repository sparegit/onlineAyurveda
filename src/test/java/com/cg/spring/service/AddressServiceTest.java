package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Address;

@SpringBootTest
class AddressServiceTest {

	@Autowired
	IAddressService addrService;

	@Test
	@Disabled
	void testSaveAddress() {
		Address address = new Address(115L, "660", "rathnam street", "trichy");
		Address persistedAddr = addrService.save(address);
		assertEquals(114L, persistedAddr.getAddressId());
		assertEquals("660", persistedAddr.getDoorNo());
		assertEquals("rathnam street", persistedAddr.getStreetName());
		assertEquals("trichy", persistedAddr.getCity());
	}

	@Test
	@Disabled
	void testViewAddress() {
		List<Address> addr = addrService.findAllAddresses();
		assertEquals(3, addr.size());
	}

	@Test
	@Disabled
	void testViewAddressById() {
		Address addr = addrService.findAddressById(100);
		System.out.println(addr);
		assertEquals("LBS", addr.getStreetName());
	}

	@Test
	@Disabled
	void testupdateAddress() {
		Address address = new Address();
		address.setAddressId(200);
		address.setDoorNo("454");
		address.setCity("bengaluru");
		address.setStreetName("sally street");
		Address addr = addrService.update(address);
		assertEquals("bengaluru", addr.getCity());
	}

	@Test
	@Disabled
	void testDeleteAddressById() {
		Address addr = addrService.deleteAddressById(114);
		assertEquals(114, addr.getAddressId());
	}
}
