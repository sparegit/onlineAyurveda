package com.cg.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;
import com.cg.spring.repository.ICategoryRepository;
import com.cg.spring.repository.IMedicineRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

	@Autowired
	ICategoryRepository catRepo;
	@Autowired
	IMedicineRepository medRepo;

	@Override
	public Category viewCategory(String categoryId) {
		logger.info("View category by id");
		Optional<Category> opt = catRepo.findById(categoryId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public List<Category> showAllCategory() {
		logger.info("View all categories in database");
		return catRepo.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		logger.info("Add category to the database");
		return catRepo.save(category);
	}

	@Override
	public Category deleteCategory(String categoryId) {
		logger.info("Delete category by id");
		Optional<Category> opt = catRepo.findById(categoryId);
		if (!opt.isPresent()) {
			return null;
		}
		Category cat = opt.get();
		catRepo.deleteById(categoryId);
		return cat;
	}

	@Override
	public Category updateCategory(Category category) {
		logger.info("Update category name");
		Optional<Category> opt = catRepo.findById(category.getCategoryId());
		if (!opt.isPresent()) {
			return null;
		}
		Category cat = opt.get();
		cat.setCategoryName(category.getCategoryName());
		return catRepo.save(cat);
	}

	@Override
	public Medicine viewMedicineById(String medicineId) {
		logger.info("View medicine by medicineid");
		Optional<Medicine> opt = medRepo.findById(medicineId);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

}
