package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Address;

@SpringBootTest
class AddressServiceTest {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddressServiceTest.class);

	@Autowired
	IAddressService addrService;

	// Add
	@Test
	@Disabled
	void testSaveAddress() {
		Address address = new Address(115L, "660", "rathnam street", "trichy");
		Address persistedAddr = addrService.save(address);
		logger.info(persistedAddr);
		assertEquals(114L, persistedAddr.getAddressId());
		assertEquals("660", persistedAddr.getDoorNo());
		assertEquals("rathnam street", persistedAddr.getStreetName());
		assertEquals("trichy", persistedAddr.getCity());
	}

	// ViewAll
	@Test
	@Disabled
	void testViewAddress() {
		List<Address> addr = addrService.findAllAddresses();
		logger.info(addr);
		assertEquals(3, addr.size());
	}

	// ViewById
	@Test
	@Disabled
	void testViewAddressById() {
		Address addr = addrService.findAddressById(100);
		logger.info(addr);
		assertEquals("LBS", addr.getStreetName());
	}

	// Update
	@Test
	@Disabled
	void testupdateAddress() {
		Address address = new Address(200L,"454","bengaluru","sally street");
		address.setAddressId(200L);
		address.setDoorNo("454");
		address.setCity("bengaluru");
		address.setStreetName("sally street");
		Address addr = addrService.update(address);
		logger.info(addr);
		assertEquals("bengaluru", addr.getCity());
	}

	// Delete
	@Test
	@Disabled
	void testDeleteAddressById() {
		Address addr = addrService.deleteAddressById(114L);
		logger.info(addr);
		assertEquals(114L, addr.getAddressId());
	}
}
