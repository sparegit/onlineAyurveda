package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.repository.ICategoryRepository;
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class MedicineServiceImpl implements IMedicineService {
	@Autowired
	IMedicineRepository medRepo;
	@Autowired
	ICategoryRepository catRepo;
	@Autowired
	IOrderRepository orderRepo;

	@Override
	public Medicine viewMedicine(String medicineId) {
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public List<Medicine> showAllMedicine() {
		return medRepo.findAll();
	}

	@Override
	public Medicine addMedicine(Medicine medicine) {
		return medRepo.save(medicine);
	}

	@Override
	public Medicine deleteMedicine(String medicineId) {
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		Medicine med = opt.get();
		medRepo.deleteById(medicineId);
		return med;
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) {
		Optional<Medicine> opt = medRepo.findById(medicine.getMedicineId());
		if (!opt.isPresent()) {
			return null;
		}
		Medicine med = opt.get();
		med.setMedicineName(medicine.getMedicineName());
		med.setMedicineCost(medicine.getMedicineCost());
		med.setMfd(medicine.getMfd());
		med.setExpiryDate(medicine.getExpiryDate());
		return medRepo.save(med);
	}

	@Override
	public Category viewCategoryById(String categoryId) {
		Optional<Category> opt = catRepo.findById(categoryId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public Medicine updateMedicineName(String medicineId, Medicine medicine) {
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		Medicine med = opt.get();
		med.setMedicineName(medicine.getMedicineName());

		return medRepo.save(med);
	}

	@Override
	public Medicine findByMedicineName(String medicineName) {
		return medRepo.findByMedicineName(medicineName);
	}

	@Override
	public List<Order> getOrderList() {
		return orderRepo.findAll();
	}

}
