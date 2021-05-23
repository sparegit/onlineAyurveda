package com.cg.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private long cartId;
	@NonNull
	private int customerId;
	@NonNull
	private String medicineName;
	@NonNull
	private int quantity;
	@NonNull
	private double price;
	@NonNull
	private double totalAmount;

	// ManyToMany
	@ManyToMany(targetEntity = Medicine.class, cascade = CascadeType.ALL)
	@JoinTable(name = "cart_med", joinColumns = { @JoinColumn(name = "cart_id") }, inverseJoinColumns = {
			@JoinColumn(name = "med_id") })
	@NonNull
	private List<Medicine> medicineList = new ArrayList<>();

	@JsonManagedReference
	public List<Medicine> getMedicine() {
		return medicineList;
	}

	// Constructor
	public Cart() {
	}
}