package com.cg.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	private String customerPassword;
	@NonNull
	private Long mobileNumber;
	@NonNull
	private String emailId;

	@OneToOne(targetEntity = Cart.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_fk", referencedColumnName = "cartId")
	@NonNull
	private Cart cart;

	// One to One Mapping
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_fk", referencedColumnName = "addressId")
	private Address address;
	@JsonIgnore
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Order> orders;
	// Constructor
	public Customer() {
	}
}
