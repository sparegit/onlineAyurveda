package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Address;
import com.cg.spring.repository.IAddressRepository;

@Service
public class AddressServiceImpl implements IAddressService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddressServiceImpl.class);

	@Autowired
	IAddressRepository addrRepo;
	// To store the address
	@Override
	public Address save(Address address) {
		logger.info("Adding address to the database");
		return addrRepo.save(address);
	}
	// To List all the address called from the controller class and send back to the
		// Controller
	@Override
	public List<Address> findAllAddresses() {
		logger.info("View all addresses");
		return addrRepo.findAll();
	}
	// Get and retrieve a specific Address based on the given id else throws
		// AddressNotFound Exception
	@Override
	public Address findAddressById(long addressid) {
		logger.info("View address by id");
		Optional<Address> addr = addrRepo.findById(addressid);
		if (!addr.isPresent()) {
			return null;
		}
		return addr.get();
	}
	// To update the adddress based on the given object
	@Override
	public Address update(Address address) {
		logger.info("Update address in the database");
		Optional<Address> addr = addrRepo.findById(address.getAddressId());
		if (!addr.isPresent()) {
			return null;
		}

		addr.get().setAddressId(address.getAddressId());	
		addr.get().setLocation(address.getLocation());
		addr.get().setCity(address.getCity());

		return addrRepo.save(addr.get());
	}
	// To update the adddress based on the given id
	@Override
	public Address deleteAddressById(long addressid) {
		logger.info("Deleting address by id");
		Optional<Address> addr = addrRepo.findById(addressid);
		if (!addr.isPresent()) {
			return null;
		}
		addrRepo.deleteById(addressid);
		return addr.get();
	}
}
