package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Address;
import com.cg.spring.repository.IAddressRepository;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	IAddressRepository addrRepo;

	@Override
	public Address save(Address address) {
		return addrRepo.save(address);
	}

	@Override
	public List<Address> findAllAddresses() {
		return addrRepo.findAll();
	}

	@Override
	public Address findAddressById(long addressid) {
		Optional<Address> addr = addrRepo.findById(addressid);
		if (!addr.isPresent()) {
			return null;
		}
		return addr.get();
	}

	@Override
	public Address update(Address address) {
		Optional<Address> addr = addrRepo.findById(address.getAddressId());
		if (!addr.isPresent()) {
			return null;
		}

		addr.get().setAddressId(address.getAddressId());
		addr.get().setDoorNo(address.getDoorNo());
		addr.get().setStreetName(address.getStreetName());
		addr.get().setCity(address.getCity());

		return addrRepo.save(addr.get());
	}

	@Override
	public Address deleteAddressById(long addressid) {
		Optional<Address> addr = addrRepo.findById(addressid);
		if (!addr.isPresent()) {
			return null;
		}
		addrRepo.delete(addr.get());
		return addr.get();
	}
}
