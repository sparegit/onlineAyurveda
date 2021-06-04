package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
