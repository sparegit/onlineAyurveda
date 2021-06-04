package com.cg.spring.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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
	String streetName;
	@NonNull
	String city;

	// Constructors
	public Address() {}

}

	