  
package com.cg.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Payment {
	@Id
	@GeneratedValue
	private int paymentId;
	private String paymentType;
	private double itemTotal;
	private double shippingFee;
	private double totalPrice;

	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "pay_fk", referencedColumnName = "paymentId ")

	private List<Order> orders;

}