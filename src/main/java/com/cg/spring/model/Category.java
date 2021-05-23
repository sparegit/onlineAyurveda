package com.cg.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
@Table(name = "category")
@Entity
public class Category {
	@Id
	@NonNull
	private String categoryId;
	
	@Size(min = 3, message = "Minimum charecters in category name should be 3.")
	@NotEmpty
	@NonNull
	private String categoryName;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "category")
	private Medicine medicine;

	// Constructors
	public Category() {
	}

}
