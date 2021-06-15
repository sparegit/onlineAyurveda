package com.cg.spring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "ordertab")
public class Order {
	@Id
	@Column(name = "orderId")
	@GeneratedValue
	private int orderId;
    @NonNull
	private LocalDate orderDate;
	@ManyToMany(targetEntity = Medicine.class, cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name = "ord_med", joinColumns = { @JoinColumn(name = "ord_id") }, inverseJoinColumns = {
			@JoinColumn(name = "med_id") })
	private List<Medicine> medicineList = new ArrayList<>();
	@NonNull
	private LocalDate dispatchDate;
	@NonNull
	private String location;
	private float totalCost;
	@NonNull
	private String paymentType;
	@NonNull
	private String status;
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.ALL,CascadeType.REFRESH})
	@JoinColumn(name="c_ord_fk",referencedColumnName = "customerId")
	@NonNull
	private Customer customer;

	
}
