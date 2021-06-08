package com.cg.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Integer> {

	// Custom methods
	Medicine findByMedicineName(String medicineName);
	
	List<Medicine> findByMedicineCategory(String medicineCategory);

}