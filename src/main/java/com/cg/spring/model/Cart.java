package com.cg.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Cart {
	// Fields
	@Id
	@NonNull
	@GeneratedValue
	private long cartId;
	@NonNull
	private int customerId;
	@NonNull
	private String medId;
	@NonNull
	private String medicineName;
	@NonNull
	private int quantity;
	@NonNull
	private double price;
	@NonNull
	private double totalAmount;
	
	//One to One Mapping
	@JsonIgnore
	@OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_fk", referencedColumnName = "customerId")
	private Customer customer;

	// Many To Many Mapping
	@JsonIgnore
	@ManyToMany(targetEntity = Medicine.class, cascade = CascadeType.ALL)
	@JoinTable(name = "cart_med", joinColumns = { @JoinColumn(name = "cart_id") }, inverseJoinColumns = {
			@JoinColumn(name = "med_id") })
	private List<Medicine> medicineList = new ArrayList<>();
	
	

	// Constructor
	public Cart() {
	}
}