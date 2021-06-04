package com.cg.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.model.Address;
import com.cg.spring.service.IAddressService;

@RestController
public class AddressController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddressController.class);

	// We are autowiring the medicine service layer to this controller layer of
	// medicine
	@Autowired
	IAddressService addrService;
	
	// This below function is used to create a new address and redirects to the
	// address service
	@PostMapping("/address")
	public ResponseEntity <Address> save(@Valid @RequestBody Address address) {
		logger.info("Adding address in database");
		return new ResponseEntity<> (addrService.save(address),HttpStatus.OK);
	}

	// This below function is used to get all the address and redirects to the
	// address service
	@GetMapping("/address")
	public ResponseEntity<List<Address>> findAllAddresses() {
		logger.info("View all address from database");
		return new ResponseEntity<>(addrService.findAllAddresses(),HttpStatus.OK);
	}

	// This below function is used to get a specific address and id as parameter and
	// redirects to the address service
	@GetMapping("/address/{id}")
	public ResponseEntity<Address> findAddressById(@PathVariable("id") long addressid) {
		logger.info("View address by id");
		return new ResponseEntity<> (addrService.findAddressById(addressid),HttpStatus.OK);
	}

	// This below function is used to update a specific address based on the give Id
	// and redirects to the address service
	@PutMapping("/address")
	public ResponseEntity<Address> update(@Valid @RequestBody Address address) {
		logger.info("Updating address details in database");
		return new ResponseEntity<>(addrService.update(address),HttpStatus.OK);
	}


	// This below function is used to delete a specific address based on the give Id
	// and redirects to the address service
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Address> deleteAddressById(@PathVariable("id") long addressid) {
		logger.info("Delete address by id");
		return new ResponseEntity<>(addrService.deleteAddressById(addressid),HttpStatus.OK);
	}
}
