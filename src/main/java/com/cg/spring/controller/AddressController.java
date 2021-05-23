package com.cg.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	IAddressService addrService;

	@PostMapping("/address")
	public Address save(@RequestBody Address address) {
		return addrService.save(address);
	}

	@GetMapping("/address")
	public List<Address> findAllAddresses() {
		return addrService.findAllAddresses();
	}

	@GetMapping("/address/{id}")
	public Address findAddressById(@PathVariable("id") long addressid) {
		return addrService.findAddressById(addressid);
	}

	@PutMapping("/address")
	public Address update(@RequestBody Address address) {
		return addrService.update(address);
	}

	@DeleteMapping("/address/{id}")
	public Address deleteAddressById(@PathVariable("id") long addressid) {
		return addrService.deleteAddressById(addressid);
	}
}
