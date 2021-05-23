package com.cg.spring.service;

import java.util.List;

import com.cg.spring.model.Address;

public interface IAddressService {

	Address findAddressById(long addressid);

	List<Address> findAllAddresses();

	Address save(Address address);

	Address update(Address address);

	Address deleteAddressById(long addressid);
}
