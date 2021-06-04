package com.cg.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.exception.CategoryNotFoundException;
import com.cg.spring.exception.MedicineNotFoundException;
import com.cg.spring.service.ICategoryService;

@RestController
public class CategoryController {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CategoryController.class);

	@Autowired
	ICategoryService catService;

	// READ
	@GetMapping("/category/id/{id}")
	public ResponseEntity<Category> viewCategory(@PathVariable("id") String categoryId) {
		logger.info("View category by id");
		Category cat = catService.viewCategory(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// showAll
	@GetMapping("/category")
	public ResponseEntity<List<Category>> showAllCategory() {
		logger.info("View all category from database");
		return new ResponseEntity<>(catService.showAllCategory(), HttpStatus.OK);
	}

	// WRITE
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
		logger.info("Add category to the database");
		return new ResponseEntity<>(catService.addCategory(category), HttpStatus.CREATED);
	}

	// DELETE
	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") String categoryId) {
		logger.info("Delete category by id");
		Category cat = catService.deleteCategory(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id to delete" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") String categoryId,
			@RequestBody Category category) {
		logger.info("Update category by id");
		Category cat = catService.updateCategory(category);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id to update" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// find Medicine By Id
	@GetMapping("/category/{id}")
	public ResponseEntity<Medicine> viewMedicineById(@PathVariable("id") String medicineId) {
		logger.info("View category by medicine id");
		Medicine med = catService.viewMedicineById(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

}
