package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import com.cg.spring.model.Category;
import com.cg.spring.model.Medicine;

@SpringBootTest
class MedicineServiceTest {

	org.apache.logging.log4j.Logger logger = LogManager.getLogger(MedicineServiceTest.class);
	
	@Autowired
	IMedicineService medService;

	// Testing whether the medicine database has medicine or null.
	@Test
	@Disabled
	public void testshowAllMedicine() {
		List<Medicine> medicines = medService.showAllMedicine();
		logger.info(medicines);
		assertEquals(4, medicines.size());
	}

	// Testing whether the given id fetches the given medicine or not.
	@Disabled
	@Test
	public void viewMedicine() {
		Medicine med = medService.viewMedicine(1);
		logger.info(med);
		assertEquals("ayurveda", med.getMedicineName());
	}

	// Testing whether the medicine gets removed from the database.
	@Disabled
	@Test
	public void deleteMedicine() {
		Medicine med = medService.deleteMedicine(2);
		logger.info(med);
		assertEquals("2", med.getMedicineId());
	}

	// Testing whether the medicine gets added to the database.
	@Test
	@Disabled
	public void addMedicine() {
		Medicine med = new Medicine(3, "ayurveda", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"),"fever","asdfghj");
		Medicine persistedMed = medService.addMedicine(med);
		logger.info(persistedMed);
		assertEquals("3", persistedMed.getMedicineId());
		assertEquals("ayurveda", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());
	}

	// Testing whether the medicine gets updated to the database.
	@Disabled
	@Test
	public void updateMedicine() {
		Medicine med = new Medicine(6, "Chandana", 600f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"),"fever","asdfghj");
		med.setMedicineId(6);
		med.setMedicineName("Chandana");
		med.setMedicineCost(600f);
		med.setMfd(LocalDate.parse("2020-03-13"));
		med.setExpiryDate(LocalDate.parse("2022-06-22"));

		Medicine updatemed = medService.updateMedicine(med);
		logger.info(updatemed);
		assertEquals("Chandana", updatemed.getMedicineName());

	}
	// Testing whether the given id fetches the given category or not.
	/*@Test
	@Disabled
	public void viewCategoryById() {
		Category cat = medService.viewCategoryById(2);
		logger.info(cat);
		assertEquals("caugh", cat.getCategoryName());
	}*/

}
