package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	ICategoryService catService;

	// find all category
	@Test
	@Disabled
	public void testshowAllCategory() {
		List<Category> cat = catService.showAllCategory();
		assertEquals(4, cat.size());

	}

	// find category by id
	@Test
	@Disabled
	public void viewCategory() {
		Category cat = catService.viewCategory("2");
		System.out.println(cat);
		assertEquals("caugh", cat.getCategoryName());
	}

	// delete category
	@Test
	@Disabled
	public void deleteCategory() {
		Category cat = catService.deleteCategory("4");
		assertEquals("4", cat.getCategoryId());
	}

	// add category
	@Test
	@Disabled
	public void addCategory() {
		Category cat = new Category("5", "caugh");
		Category persistedCat = catService.addCategory(cat);
		assertEquals("4", persistedCat.getCategoryId());
		assertEquals("caugh", persistedCat.getCategoryName());
	}

	// update category
	@Disabled
	@Test
	public void updateCategory() {
		Category cat = new Category();
		cat.setCategoryId("3");
		cat.setCategoryName("cold");
		Category updatecat = catService.updateCategory(cat);
		assertEquals("cold", updatecat.getCategoryName());
	}

	@Test
	@Disabled
	public void viewMedicineById() {
		Medicine med = catService.viewMedicineById("1");
		assertEquals("ayurveda", med.getMedicineName());
	}

}
