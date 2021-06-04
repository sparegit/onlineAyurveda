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
	String doorNo;
	@NonNull
	@NotBlank
	String streetName;
	@NonNull
	@NotBlank
	String city;

	// Constructors
	public Address() {}

}

	