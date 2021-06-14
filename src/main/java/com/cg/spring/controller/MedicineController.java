package com.cg.spring.controller;

import javax.validation.Valid;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.spring.model.Medicine;
import com.cg.spring.exception.MedicineNotFoundException;
import com.cg.spring.service.IMedicineService;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(MedicineController.class);
	// We are autowiring the medicine service layer to this controller layer of medicine
	@Autowired
	IMedicineService medService;

	// This controller is used to get a specific medicine on basis of ID
	@GetMapping("/id/{id}")
	public ResponseEntity<Medicine> viewMedicine(@PathVariable("id") int medicineId) {
		logger.info("View medicine by id");
		Medicine med = medService.viewMedicine(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// This controller is used to return and list all the medicine found in the
	// database and request to the service to perform the action
	@GetMapping("")
	public ResponseEntity<List<Medicine>> showAllMedicine() {
		logger.info("View all medicines from database");
		return new ResponseEntity<>(medService.showAllMedicine(), HttpStatus.OK);
	}

	

	// This controller is used to create a new or add new medicine and redirects it
		// to the service layer
	@PostMapping("")
	public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
		logger.info("Add medicine by id");
		Medicine med = medService.addMedicine(medicine);
		return new ResponseEntity<>(med, HttpStatus.CREATED);
	}

	// this controller function perform deletion of a specific given medicine
		// and request the service to perform the action and returns the message as
		// deleted else throw exception
	@DeleteMapping("/{id}")
	public ResponseEntity<Medicine> deleteMedicine(@PathVariable("id") int medicineId) {
		logger.info("Delete medicine by id");
		Medicine med = medService.deleteMedicine(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id to delete" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// This function is used to update a specific medicine on basis of given
		// medicine id and returns exception if given medicine id is not found.
	@PutMapping("/{id}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") String medicineId,
		@Valid	@RequestBody Medicine medicine) {
		logger.info("Update medicine by id");
		Medicine med = medService.updateMedicine(medicine);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id to update" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	// This controller is used to get a specific category on basis of CategoryID
	/*@GetMapping("/{id}")
	public ResponseEntity<Category> viewCategoryById(@PathVariable("id") int categoryId) {
		logger.info("View category by id");
		Category cat = medService.viewCategoryById(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Medicine not found with this id" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}*/

	// This function is used to update a specific medicine on basis of given
		// medicine name and returns exception if given medicine id is not found.
	@PatchMapping("/{id}")
	public ResponseEntity<Medicine> updateMedicineName(@PathVariable("id") int medicineId,
		@Valid	@RequestBody Medicine medicine) {
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
	
	@GetMapping("/medicine/{medicineCategory}")
	public ResponseEntity<List<Medicine>> findByMedicineCategory(@PathVariable("medicineCategory") String medicineCategory){
		List<Medicine> med =  medService.findByMedicineCategory(medicineCategory);
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

}