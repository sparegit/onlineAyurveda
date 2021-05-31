package com.cg.spring.repository;




import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Customer;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findCustomerByEmailId(String email);
}
