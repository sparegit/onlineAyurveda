package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.CustomerLogin;
@Repository
public interface ICustomerLoginRepository extends JpaRepository<CustomerLogin, String> {
	

}
