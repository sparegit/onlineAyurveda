package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
//import com.cg.spring.repository.ICategoryRepository;
import com.cg.spring.repository.IMedicineRepository;
import com.cg.spring.repository.IOrderRepository;

@Service
public class MedicineServiceImpl implements IMedicineService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(MedicineServiceImpl.class);
	
	@Autowired
	IMedicineRepository medRepo;
	//@Autowired
	//ICategoryRepository catRepo;
	@Autowired
	IOrderRepository orderRepo;
	// Get a specific medicine of the given ID
	@Override
	public Medicine viewMedicine(int medicineId) {
		logger.info("View medicine by id");
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	// Used to list all the medicine from the database
	@Override
	public List<Medicine> showAllMedicine() {
		logger.info("View all medicines in database");
		return medRepo.findAll();
	}
	// Used to store the given medicine Object passed from the controller
	@Override
	public Medicine addMedicine(Medicine medicine) {
		logger.info("Add medicine to database");
		return medRepo.save(medicine);
	}
	// Used to delete the medicine
	@Override
	public Medicine deleteMedicine(int medicineId) {
		logger.info("Delete medicine by id");
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		Medicine med = opt.get();
		medRepo.deleteById(medicineId);
		return med;
	}
	// Used to update the medicine of given object
	@Override
	public Medicine updateMedicine(Medicine medicine) {
		logger.info("Update medicines details in database");
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
	// Get a specific category of the given ID
	/*@Override
	public Category viewCategoryById(int categoryId) {
		logger.info("View category by id");
		Optional<Category> opt = catRepo.findById(categoryId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}*/
	// Used to update the medicine name of given id and object
	@Override
	public Medicine updateMedicineName(int medicineId, Medicine medicine) {
		logger.info("Update medicine name");
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		Medicine med = opt.get();
		med.setMedicineName(medicine.getMedicineName());

		return medRepo.save(med);
	}
	// Used to get medicine by given name
	@Override
	public Medicine findByMedicineName(String medicineName) {
		logger.info("View medicine by name");
		return medRepo.findByMedicineName(medicineName);
	}
	// Used to list all the orders from the database
	@Override
	public List<Order> getOrderList() {
		logger.info("Get order list");
		return orderRepo.findAll();
	}
	/*@Override
	public List<Medicine> viewMedicineListBasedOnCategory(String categoryName) {
		medRepo.viewMedicineListBasedOnCategory(categoryName).get(0);
		medRepo.viewMedicineListBasedOnCategory(categoryName);
		return medRepo.viewMedicineListBasedOnCategory(categoryName);
	}*/
	@Override
	public List<Medicine> findByMedicineCategory(String medicineCategory) {
		return medRepo.findByMedicineCategory(medicineCategory);
		 
	}
	

}
