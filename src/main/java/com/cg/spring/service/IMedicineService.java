package com.cg.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;

@Service
public interface IMedicineService {
	// Method to be override by the implementing class
	public Medicine viewMedicine(int medicineId);

	// Method to be override by the implementing class
	public List<Medicine> showAllMedicine();

	// Method to be override by the implementing class
	public Medicine addMedicine(Medicine medicine);

	// Method to be override by the implementing class
	public Medicine deleteMedicine(int medicineId);

	// Method to be override by the implementing class
	public Medicine updateMedicine(Medicine medicine);

	// Method to be override by the implementing class
	public Medicine updateMedicineName(int medicineId, Medicine medicine);

	// Method to be override by the implementing class
	public List<Order> getOrderList();

	// Method to be override by the implementing class
	// custom methods
	// find by name
	public Medicine findByMedicineName(String medicineName);

	//Method to be override by the implementing class
	//custom method
	//find by category
	public List<Medicine> findByMedicineCategory(String medicineCategory);
	
}

