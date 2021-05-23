package com.cg.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;

@Service
public interface ICategoryService {

	public Category viewCategory(String categoryId);

	public List<Category> showAllCategory();

	public Category addCategory(Category category);

	public Category deleteCategory(String categoryId);

	public Category updateCategory(Category category);

	public Medicine viewMedicineById(String medicineId);

}
