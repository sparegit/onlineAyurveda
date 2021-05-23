package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;

@SpringBootTest
class MedicineServiceTest {

	@Autowired
	IMedicineService medService;

	// find all medicine
	@Test
	@Disabled
	public void testshowAllMedicine() {
		List<Medicine> medicines = medService.showAllMedicine();
		assertEquals(4, medicines.size());
	}

	// findMedicineById
	@Disabled
	@Test
	public void viewMedicine() {
		Medicine med = medService.viewMedicine("1");
		assertEquals("ayurveda", med.getMedicineName());
	}

	// DeleteMedicineById
	@Disabled
	@Test
	public void deleteMedicine() {
		Medicine med = medService.deleteMedicine("2");
		assertEquals("2", med.getMedicineId());
	}

	// Add Medicine
	@Test
	@Disabled
	public void addMedicine() {
		Medicine med = new Medicine("3", "ayurveda", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Medicine persistedMed = medService.addMedicine(med);
		assertEquals("3", persistedMed.getMedicineId());
		assertEquals("ayurveda", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());
	}

	// Update Medicine
	@Disabled
	@Test
	public void updateMedicine() {
		Medicine med = new Medicine("6", "Chandana", 600f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		med.setMedicineId("6");
		med.setMedicineName("Chandana");
		med.setMedicineCost(600f);
		med.setMfd(LocalDate.parse("2020-03-13"));
		med.setExpiryDate(LocalDate.parse("2022-06-22"));

		Medicine updatemed = medService.updateMedicine(med);
		assertEquals("Chandana", updatemed.getMedicineName());

	}

	@Test
	@Disabled
	public void viewCategoryById() {
		Category cat = medService.viewCategoryById("2");
		assertEquals("caugh", cat.getCategoryName());
	}

}
