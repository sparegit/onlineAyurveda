package com.cg.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private int orderId;
    @NonNull
	private LocalDate orderDate;
	@ManyToMany(targetEntity = Medicine.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ord_med", joinColumns = { @JoinColumn(name = "ord_id") }, inverseJoinColumns = {
			@JoinColumn(name = "med_id") })
	private List<Medicine> medicineList;
	@NonNull
	private LocalDate dispatchDate;
	@NotEmpty
	private float totalCost;
	@NonNull
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="c_ord_fk",referencedColumnName = "customerId")
	@NonNull
	private Customer customer;
	
}
