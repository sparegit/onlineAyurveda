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

import com.cg.spring.model.Address;
import com.cg.spring.repository.IAddressRepository;

@ExtendWith(SpringExtension.class)
public class AddressServiceMockitoTest {
	@InjectMocks
	AddressServiceImpl addrService;

	@MockBean
	IAddressRepository addrRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Disabled
	void testSaveAddress() {
		Address address = new Address(151L, "55/80", "rose street", "madurai");
		Mockito.when(addrRepo.save(address)).thenReturn(address);

		assertEquals(151L, address.getAddressId());
	}

	@Test
	@Disabled
	void testViewAddress() {
		Address address1 = new Address(112L, "77", "sally street", "chennai");
		Address address2 = new Address(113L, "669", "stanley street", "trichy");
		List<Address> addressList = new ArrayList<>();
		addressList.add(address1);
		addressList.add(address2);
		Mockito.when(addrRepo.findAll()).thenReturn(addressList);
		List<Address> address = addrService.findAllAddresses();
		assertEquals(2, address.size());
	}

	@Test
	@Disabled
	void testViewAddressById() {
		Address address = new Address(112L, "77", "sally street", "chennai");
		Mockito.when(addrRepo.findById(112L)).thenReturn(Optional.of(address));
		assertEquals(112L, address.getAddressId());
	}

	@Test
	@Disabled
	void testupdateAddress() {
		Address address = new Address(113L, "669", "stanley street", "trichy");
		Mockito.when(addrRepo.findById(113L)).thenReturn(Optional.of(address));
		Mockito.when(addrRepo.save(address)).thenReturn(address);
		Address address1 = addrService.update(address);
		assertEquals("stanley street", address1.getLocation());
	}

	@Test
	@Disabled
	void testDeleteAddressById() {
		Address address = new Address(111L, "88", "Cannon street", "chennai");
		Mockito.when(addrRepo.findById(111L)).thenReturn(Optional.of(address));
		addrService.deleteAddressById(111L);
		assertEquals(111L, address.getAddressId());
	}

}
