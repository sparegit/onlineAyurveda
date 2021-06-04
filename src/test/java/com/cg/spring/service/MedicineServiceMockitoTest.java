package com.cg.spring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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

import com.cg.spring.model.Medicine;
import com.cg.spring.repository.IMedicineRepository;

@ExtendWith(SpringExtension.class)
class MedicineServiceMockitoTest {

	@InjectMocks
	MedicineServiceImpl medService;

	@MockBean
	IMedicineRepository medRepo;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddMedicine() {
		Medicine med = new Medicine(6, "chandana", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));

		Mockito.when(medRepo.save(med)).thenReturn(med);
		Medicine persistedMed = medService.addMedicine(med);
		assertEquals("6", persistedMed.getMedicineId());
		assertEquals("chandana", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());

	}

	@Test
	void testViewMedicine() {
		Medicine med = new Medicine(1, "chandana", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Mockito.when(medRepo.findById(1)).thenReturn(Optional.of(med));
		Medicine persistedMed = medService.viewMedicine(1);
		assertEquals("1", persistedMed.getMedicineId());
		assertEquals("chandana", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());
	}

	@Test
	void testShowAllMedicine() {
		Medicine med1 = new Medicine(1, "chandana", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Medicine med2 = new Medicine(2, "tulasi", 500f, LocalDate.parse("2010-08-01"), LocalDate.parse("2013-05-21"));
		Medicine med3 = new Medicine(3, "Chandana", 600f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Medicine med4 = new Medicine(4, "Turmeric", 600f, LocalDate.parse("2019-07-06"),
				LocalDate.parse("2022-05-21"));
		Medicine med5 = new Medicine(5, "Turmeric", 600f, LocalDate.parse("2019-07-06"),
				LocalDate.parse("2022-05-21"));
		Medicine med6 = new Medicine(10, "Ashvagandha", 500f, LocalDate.parse("2021-05-05"),
				LocalDate.parse("2019-05-05"));
		List<Medicine> medicineList = new ArrayList<>();
		medicineList.add(med1);
		medicineList.add(med2);
		medicineList.add(med3);
		medicineList.add(med4);
		medicineList.add(med5);
		medicineList.add(med6);
		Mockito.when(medRepo.findAll()).thenReturn(medicineList);
		List<Medicine> medicines = medService.showAllMedicine();
		assertEquals(6, medicines.size());
	}

	@Test
	void testupdateMedicine() {
		Medicine med = new Medicine(1, "chandana", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Mockito.when(medRepo.findById(1)).thenReturn(Optional.of(med));
		Mockito.when(medRepo.save(med)).thenReturn(med);
		Medicine persistedMed = medService.updateMedicine(med);
		assertEquals(1, persistedMed.getMedicineId());
		assertEquals("chandana", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());
	}

	@Test
	void testdeleteMedicine() {
		Medicine med = new Medicine(1, "chandana", 499f, LocalDate.parse("2020-03-13"),
				LocalDate.parse("2022-06-22"));
		Mockito.when(medRepo.findById(1)).thenReturn(Optional.of(med));
		medRepo.deleteById(1);
		Medicine persistedMed = medService.deleteMedicine(1);
		assertEquals(1, persistedMed.getMedicineId());
		assertEquals("chandana", persistedMed.getMedicineName());
		assertEquals(499f, persistedMed.getMedicineCost());
		assertEquals(LocalDate.parse("2020-03-13"), persistedMed.getMfd());
		assertEquals(LocalDate.parse("2022-06-22"), persistedMed.getExpiryDate());
	}

}
