package com.cg.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.model.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}
