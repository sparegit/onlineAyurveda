package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, String> {

	// Custom methods
	Medicine findByMedicineName(String medicineName);

}
