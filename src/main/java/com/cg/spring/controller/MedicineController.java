package com.cg.spring.controller;

import javax.validation.Valid;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.model.Order;
import com.cg.spring.exception.CategoryNotFoundException;
import com.cg.spring.exception.MedicineNotFoundException;
import com.cg.spring.service.IMedicineService;

@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(MedicineController.class);

	@Autowired
	IMedicineService medService;

	// READ
	@GetMapping("/id/{id}")
	public ResponseEntity<Medicine> viewMedicine(@PathVariable("id") String medicineId) {
		logger.info("View medicine by id");
		Medicine med = medService.viewMedicine(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// showAll
	@GetMapping("")
	public ResponseEntity<List<Medicine>> showAllMedicine() {
		logger.info("View all medicines from database");
		return new ResponseEntity<>(medService.showAllMedicine(), HttpStatus.OK);
	}

	// find all order
	@GetMapping("/order")
	public ResponseEntity<List<Order>> getOrderList() {
		logger.info("Get all Orders");
		return new ResponseEntity<>(medService.getOrderList(), HttpStatus.OK);
	}

	// WRITE
	@PostMapping("")
	public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
		logger.info("View medicine by id");
		Medicine med = medService.addMedicine(medicine);
		return new ResponseEntity<>(med, HttpStatus.CREATED);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Medicine> deleteMedicine(@PathVariable("id") String medicineId) {
		logger.info("Delete medicine by id");
		Medicine med = medService.deleteMedicine(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id to delete" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") String medicineId,
			@RequestBody Medicine medicine) {
		logger.info("Update medicine by id");
		Medicine med = medService.updateMedicine(medicine);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id to update" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// find Category By Id
	@GetMapping("/{id}")
	public ResponseEntity<Category> viewCategoryById(@PathVariable("id") String categoryId) {
		logger.info("View category by id");
		Category cat = medService.viewCategoryById(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Medicine not found with this id" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// UPDATE
	// Updating specific property
	@PatchMapping("/{id}")
	public ResponseEntity<Medicine> updateMedicineName(@PathVariable("id") String medicineId,
			@RequestBody Medicine medicine) {
		logger.info("Update medicine by id");
		Medicine med = medService.updateMedicineName(medicineId, medicine);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id to update");
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// find medicine by name
	@GetMapping("/name/{name}")
	public ResponseEntity<Medicine> findByMedicineName(@PathVariable("name") String medicineName) {
		logger.info("View medicine by name");
		Medicine med = medService.findByMedicineName(medicineName);
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

}
