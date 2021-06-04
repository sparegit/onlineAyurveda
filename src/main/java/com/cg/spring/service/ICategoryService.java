package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;

@Service
public interface ICategoryService {

	// Method to be override by the implementing class
		public Category viewCategory(String categoryId);

		// Method to be override by the implementing class
		public List<Category> showAllCategory();

		// Method to be override by the implementing class
		public Category addCategory(Category category);

		// Method to be override by the implementing class
		public Category deleteCategory(String categoryId);

		// Method to be override by the implementing class
		public Category updateCategory(Category category);

		// Method to be override by the implementing class
		public Medicine viewMedicineById(String medicineId);

}
