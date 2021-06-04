package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.spring.model.Category;
import com.cg.spring.repository.ICategoryRepository;

@ExtendWith(SpringExtension.class)
class CategoryServiceMockitoTest {

	@InjectMocks
	CategoryServiceImpl catService;

	@MockBean
	ICategoryRepository catRepo;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddCategory() {
		Category cat = new Category(1, "fever");
		Mockito.when(catRepo.save(cat)).thenReturn(cat);
		Category persistedCat = catService.addCategory(cat);
		assertEquals("1", persistedCat.getCategoryId());
		assertEquals("fever", persistedCat.getCategoryName());
	}

	@Test
	void testViewCategory() {
		Category cat = new Category(1, "fever");
		Mockito.when(catRepo.findById(1)).thenReturn(Optional.of(cat));
		Category persistedCat = catService.viewCategory(1);
		assertEquals("1", persistedCat.getCategoryId());
		assertEquals("fever", persistedCat.getCategoryName());
	}

	@Test
	void testShowAllCategory() {
		Category cat1 = new Category(1, "fever");
		Category cat2 = new Category(2, "fever");
		Category cat3 = new Category(3, "fever");
		List<Category> categoryList = new ArrayList<>();
		categoryList.add(cat1);
		categoryList.add(cat2);
		categoryList.add(cat3);
		Mockito.when(catRepo.findAll()).thenReturn(categoryList);
		List<Category> categories = catService.showAllCategory();
		assertEquals(3, categories.size());
	}

	@Test
	void testupdateCategory() {
		Category cat = new Category(1, "fever");
		Mockito.when(catRepo.findById(1)).thenReturn(Optional.of(cat));
		Mockito.when(catRepo.save(cat)).thenReturn(cat);
		Category persistedCat = catService.updateCategory(cat);
		assertEquals("1", persistedCat.getCategoryId());
		assertEquals("fever", persistedCat.getCategoryName());
	}

	@Test
	void testdeleteCategory() {
		Category cat = new Category(1, "fever");
		Mockito.when(catRepo.findById(1)).thenReturn(Optional.of(cat));
		catRepo.deleteById(1);
		Category persistedCat = catService.deleteCategory(1);
		assertEquals("1", persistedCat.getCategoryId());
		assertEquals("fever", persistedCat.getCategoryName());
	}

}
