package com.cg.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
@Table(name = "medicine")
@Entity
public class Medicine {
	@Id
	@Column(name = "medicine_id")
	
	@GeneratedValue
	private int medicineId;
	
	@NonNull
	@Size(min = 3, message = "Minimum charecters in medicine name should be 3.")
	@NotEmpty
	@Column(unique = true, name = "medicine_name", nullable = false)
	
	private String medicineName;
	
	@NonNull
	@Column(name = "medicine_cost", nullable = false)
	private float medicineCost;
	
	@NonNull
	@Column(name = "mfd", nullable = false)
	private LocalDate mfd;
	
	@NonNull
	@Column(name = "expiry_date", nullable = false)
	private LocalDate expiryDate;
	
	@NonNull
	@Column(name = "medicine_quantity", nullable = false)
	private int medicineQuantity = 1;

	public Medicine() {
	}


	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "medicineList",fetch = FetchType.EAGER)
	private List<Order> orderList;

	
}
