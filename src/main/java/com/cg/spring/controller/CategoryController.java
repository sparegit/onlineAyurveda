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
	// We are autowiring the category service layer to this controller layer of
	// category
	@Autowired
	ICategoryService catService;

	// This controller is used to get a specific category on basis of ID
	@GetMapping("/category/id/{id}")
	public ResponseEntity<Category> viewCategory(@PathVariable("id") int categoryId) {
		logger.info("View category by id");
		Category cat = catService.viewCategory(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// This controller is used to return and list all the categories found in the
	// database and request to the service to perform the action
	@GetMapping("/category")
	public ResponseEntity<List<Category>> showAllCategory() {
		logger.info("View all category from database");
		return new ResponseEntity<>(catService.showAllCategory(), HttpStatus.OK);
	}

	// This controller is used to create a new or add new category and redirects it
	// to the service layer
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
		logger.info("Add category to the database");
		return new ResponseEntity<>(catService.addCategory(category), HttpStatus.CREATED);
	}

	// this controller function perform deletion of a specific given category
	// and request the service to perform the action and returns the message as
	// deleted else throw exception
	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") int categoryId) {
		logger.info("Delete category by id");
		Category cat = catService.deleteCategory(categoryId);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id to delete" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// This function is used to update a specific category on basis of given
	// category id and returns exception if given category id is not found.
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") String categoryId,
		@Valid	@RequestBody Category category) {
		logger.info("Update category by id");
		Category cat = catService.updateCategory(category);
		if (cat == null) {
			throw new CategoryNotFoundException("Category not found with this id to update" + categoryId);
		}
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	// This controller is used to get a specific medicine on basis of medicineId
	@GetMapping("/category/{id}")
	public ResponseEntity<Medicine> viewMedicineById(@PathVariable("id") int medicineId) {
		logger.info("View category by medicine id");
		Medicine med = catService.viewMedicineById(medicineId);
		if (med == null) {
			throw new MedicineNotFoundException("Medicine not found with this id" + medicineId);
		}
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

}
