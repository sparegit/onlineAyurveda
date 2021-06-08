package com.cg.spring.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
@Data
@RequiredArgsConstructor
@ToString
@Entity
public class Address {
	// Fields
	@Id
	@NonNull
	@GeneratedValue
	long addressId;
	@NonNull
	@NotBlank
	String location;
	@NonNull
	@NotBlank
	String city;
	@NonNull
	@NotBlank
	String zip;

	// Constructors
	public Address() {}

}

	