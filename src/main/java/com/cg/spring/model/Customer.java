package com.cg.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	@GeneratedValue
	private int customerId;
	@NonNull
	@NotEmpty
	@Size(min = 3 ,message = "Minimum characters in name must be 3")
	private String customerName;
	@NonNull
	@Size(min=5, max = 15, message="Minimum characters in password")
	@NotEmpty
	private String customerPassword;
	@Min(10)
	
	@NonNull
	private Long mobileNumber;
	@NonNull
	@NotEmpty
	@Email
	private String email;
	
	

	// One to One Mapping
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_Id", referencedColumnName = "addressId")
	private Address address;
	@JsonIgnore
	//One to One Mapping
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_Id", referencedColumnName = "cartId")
	private Cart cart;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> orderListOfaCustomer=new ArrayList<>();
	
	
	// Constructor
	public Customer() {
	}
}
