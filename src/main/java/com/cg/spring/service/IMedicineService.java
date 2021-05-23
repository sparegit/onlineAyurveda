package com.cg.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;

@Service
public interface IMedicineService {
	public Medicine viewMedicine(String medicineId);

	public List<Medicine> showAllMedicine();

	public Medicine addMedicine(Medicine medicine);

	public Medicine deleteMedicine(String medicineId);

	public Medicine updateMedicine(Medicine medicine);

	public Category viewCategoryById(String categoryId);

	public Medicine updateMedicineName(String medicineId, Medicine medicine);

	public List<Order> getOrderList();

	// custom methods
	// find by name
	public Medicine findByMedicineName(String medicineName);

}
