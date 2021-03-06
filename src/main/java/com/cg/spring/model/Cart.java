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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@GeneratedValue
	private long cartId;
	@NonNull
	private double price;
	@NonNull
	private int quantity;
	@NonNull
	private double totalAmount;
	

	// Many To Many Mapping
	@JsonIgnore
	@ManyToMany(targetEntity = Medicine.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "medicine_Id", referencedColumnName = "medicineId")
	private List<Medicine> medicineList = new ArrayList<>();
	
	

	// Constructor
	public Cart() {
	}
}