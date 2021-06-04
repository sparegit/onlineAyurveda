package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Address;

public interface IAddressService {

	// Method to be override by the implementing class
		Address findAddressById(long addressid);

		// Method to be override by the implementing class
		List<Address> findAllAddresses();

		// Method to be override by the implementing class
		Address save(Address address);

		// Method to be override by the implementing class
		Address update(Address address);

		// Method to be override by the implementing class
		Address deleteAddressById(long addressid);
}
