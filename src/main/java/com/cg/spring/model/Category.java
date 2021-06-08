/*package com.cg.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
	@GeneratedValue
	private int categoryId;
	
	@Size(min = 3, message = "Minimum character in category name should be 3.")
	@NonNull
	@NotBlank
	private String categoryName;

	
	// Constructors
	public Category() {
	}

}*/
