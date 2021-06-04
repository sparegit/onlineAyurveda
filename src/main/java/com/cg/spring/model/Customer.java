package com.cg.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
@Entity
public class Customer {
	// Fields
	@Id
	@NonNull
	@GeneratedValue
	private int customerId;
	@NonNull
	private String customerName;
	@NonNull
	@Size(min=8,message="Minimum characters in password")
	@NotEmpty
	private String customerPassword;
	@NonNull
	private Long mobileNumber;
	@NonNull
	private String emailId;
	
	

	// One to One Mapping
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk", referencedColumnName = "addressId")
	private Address address;

	// Constructor
	public Customer() {
	}
}
