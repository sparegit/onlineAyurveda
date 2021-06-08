package com.cg.spring.dto;

import java.util.ArrayList;
import java.util.List;

import com.cg.spring.model.Customer;
import com.cg.spring.model.Medicine;

import lombok.Data;

@Data
public class Cartdto {
	
	private long cartId;
	private List<Medicine> medicineList = new ArrayList();
	private Customer customer;
	private int quantity=1;
	private double price;
	private double totalamount;
	
}
