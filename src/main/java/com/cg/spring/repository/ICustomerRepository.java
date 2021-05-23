package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	// find customer based on customer name
	// @Query("select c from Customer where c.customerName= :n" )
	// public Customer getCustomerByName(@Param("n")String customerName);
}
